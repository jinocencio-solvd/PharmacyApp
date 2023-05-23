package misc;

import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Employee;
import pharmacy.Pharmacy;
import register.Register;
import setup.AppConfig;
import utils.PharmacyTechnicianSupplier;

public class CashierRunnable implements Runnable {

    private static final Logger LOG = LogManager.getLogger(CashierRunnable.class);
    private static PharmacyTechnicianSupplier pharmacyTechnicianSupplier = new PharmacyTechnicianSupplier();
    private static Semaphore semaphore = new Semaphore(1);

    private Employee cashier;
    private ConcurrentCustomerLine customerLine;
    private Register register;
    private Pharmacy pharmacy;
    private int numCustomersServed;
    private int customersServedLimit;

    public CashierRunnable(Pharmacy pharmacy, ConcurrentCustomerLine customerLine,
        int customersServedLimit) {
        this.customerLine = customerLine;
        this.pharmacy = pharmacy;
        this.cashier = pharmacyTechnicianSupplier.get();
        this.register = new Register(this.cashier);
        this.numCustomersServed = 0;
        this.customersServedLimit = customersServedLimit;
    }

    public CashierRunnable(Pharmacy pharmacy, ConcurrentCustomerLine customerLine) {
        this.customerLine = customerLine;
        this.pharmacy = pharmacy;
        this.cashier = pharmacyTechnicianSupplier.get();
        this.register = new Register(this.cashier);
        this.numCustomersServed = 0;
        this.customersServedLimit = 1;
    }

    public Employee getCashier() {
        return cashier;
    }

    // TODO: Implement isCustomerLimitReached() to allow for concurrent processing.
    //  Currently, cashier's will process CUSTOMERS_SERVED_LIMIT at a time
    //  rather than concurrently. Need for greater scope of synchronization?
    public boolean isCustomerLimitReached() {
        return numCustomersServed == customersServedLimit;
    }

    @Override
    public void run() {
        LOG.trace(this.cashier.getName() + " is on cashier duties");

        while (customerLine.hasNext()) {
            AbstractCustomer nextCustomer = null;
            try {
                semaphore.acquire();
                if (customerLine.hasNext()) {
                    nextCustomer = customerLine.getNextCustomer();
                }
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (nextCustomer != null) {
                    if(AppConfig.SHOW_CASHIER_RECEIVED_CUSTOMER) LOG.info("Cashier " + cashier.getName() + " got next customer "
                        + nextCustomer.getName());
                    register.setCustomer(nextCustomer);
                    register.setCart(nextCustomer.getCart());

                    if (nextCustomer.isPatient()) {
                        register.processPrescriptionAndAddMedicationsToCart(
                            pharmacy.getFilledPrescriptions());
                    }

                    LOG.trace("From " + Thread.currentThread().getName());
                    LOG.trace(
                        "Cashier " + register.getEmployee().getName()
                            + " is scanning cart items for "
                            + nextCustomer.getName());
                    register.scanAllProductsInCart();
                    register.processTransaction();
                    if (register.getTransactionCompleted()) {
                        register.printReceipt();
                    } else {
                        LOG.error(
                            "Unable to complete customer transaction for "
                                + nextCustomer.getName());
                    }
                    if(AppConfig.SHOW_CASHIER_FINISHED_TXN)LOG.info("Cashier " + cashier.getName() + " finished txn with customer "
                        + nextCustomer.getName());
                    numCustomersServed++;
                }
            }
            if(AppConfig.SHOW_CASHIER_TOTAL_CUSTOMERS) LOG.info(
                numCustomersServed + " current total customers served by Cashier: " + cashier.getName());
        }
    }
}
