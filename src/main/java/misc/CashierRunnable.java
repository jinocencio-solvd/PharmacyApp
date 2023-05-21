package misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Employee;
import pharmacy.Pharmacy;
import register.Register;
import setup.PharmacyTechnicianSupplier;

public class CashierRunnable implements Runnable {

    private static final Logger LOG = LogManager.getLogger(CashierRunnable.class);
    private static PharmacyTechnicianSupplier pharmacyTechnicianSupplier = new PharmacyTechnicianSupplier();

    private Employee cashier;
    private ConcurrentCustomerLine customerLine;
    private Register register;
    private Pharmacy pharmacy;
    private int numCustomersServed;
    private final int CUSTOMERS_SERVED_LIMIT = 3;

    public CashierRunnable(Pharmacy pharmacy,
        ConcurrentCustomerLine customerLine) {
        this.customerLine = customerLine;
        this.pharmacy = pharmacy;
        this.cashier = pharmacyTechnicianSupplier.get();
        this.register = new Register(this.cashier);
        this.numCustomersServed = 0;
    }

    public boolean isCustomerLimitReached() {
        return numCustomersServed == CUSTOMERS_SERVED_LIMIT;
    }

    public Employee getCashier() {
        return cashier;
    }

    @Override
    public void run() {
        LOG.info(this.cashier.getName() + " is on cashier duties");
        while (customerLine.hasNext() && !isCustomerLimitReached()) {
            AbstractCustomer nextCustomer = customerLine.getNextCustomer(); //dequeue
            register.setCustomer(nextCustomer);
            register.setCart(nextCustomer.getCart());

            if (nextCustomer.isPatient()) {
                LOG.info(nextCustomer.getName() + " is a patient.");
                register.processPrescriptionAndAddMedicationsToCart(
                    pharmacy.getFilledPrescriptions());
            }

            LOG.trace("From " + Thread.currentThread().getName());
            LOG.info("Cashier " + register.getEmployee().getName() + " is scanning cart items for "
                + nextCustomer.getName());
            register.scanAllProductsInCart();
            register.processTransaction();
            if (register.getTransactionCompleted()) {
                register.printReceipt();
            } else {
                LOG.error("Unable to complete customer transaction for " + nextCustomer.getName());
            }
            numCustomersServed++;
        }
        LOG.info(numCustomersServed + " total customers served by Cashier: " + cashier.getName());
    }
}
