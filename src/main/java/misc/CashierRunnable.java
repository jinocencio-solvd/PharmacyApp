package misc;

import inventory.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Employee;
import pharmacy.Pharmacy;
import register.Register;

public class CashierRunnable implements Runnable {

    private static final Logger LOG = LogManager.getLogger(CashierRunnable.class);

    private Employee cashier;
    private ConcurrentCustomerLine customerLine;
    private Register register;
    private Pharmacy pharmacy;

    public CashierRunnable(Pharmacy pharmacy, Register register,
        ConcurrentCustomerLine customerLine) {
        this.customerLine = customerLine;
        this.register = register;
        this.pharmacy = pharmacy;
        this.cashier = register.getEmployee();
    }

    @Override
    public void run() {
        register.setEmployee(cashier);

        while (!customerLine.isEmpty()) {
            AbstractCustomer nextCustomer = customerLine.getNextCustomer(); //dequeue
            Cart customerCart = nextCustomer.getCart();
            register.setCustomer(nextCustomer);
            register.setCart(customerCart);

            if (nextCustomer.isPatient()) {
                LOG.info(nextCustomer.getName() + " is a patient.");
                register.processPrescriptionAndAddMedicationsToCart(
                    pharmacy.getFilledPrescriptions());
            }
            LOG.info("Cashier " + register.getEmployee().getName() + " is scanning cart items for "
                + nextCustomer.getName());
            register.scanAllProductsInCart();

            register.processTransaction();

            if (register.getTransactionCompleted()) {
                register.printReceipt();
            } else {
                LOG.error("Unable to complete customer transaction for " + nextCustomer.getName());
            }
        }
    }
}
