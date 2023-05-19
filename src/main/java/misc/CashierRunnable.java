package misc;

import genericLinkedList.CustomerLine;
import inventory.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Employee;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import register.Register;

public class CashierRunnable implements Runnable {

    private static final Logger LOG = LogManager.getLogger(PharmacyTechnician.class);

    private Employee cashier;
    private CustomerLine customerLine;
    private Register register;
    private Cart cart;
    private Pharmacy pharmacy;

    public CashierRunnable(Pharmacy pharmacy, Register register, Employee cashier,
        CustomerLine customerline, Cart cart) {
        this.cashier = cashier;
        this.customerLine = customerline;
        this.register = register;
        this.cart = cart;
        this.pharmacy = pharmacy;
    }

    @Override
    public void run() {
        register.setEmployee(cashier);

        while (customerLine.hasNext()) {
            AbstractCustomer nextCustomer = customerLine.getNextCustomer();
            Cart customerCart = nextCustomer.getCart();
            register.setCustomer(nextCustomer);
            register.setCart(customerCart);

            if (nextCustomer.isPatient()) {
                register.processPrescriptionAndAddMedicationsToCart(
                    pharmacy.getFilledPrescriptions());
            }
            register.scanAllProductsInCart();

            register.processTransaction();

            if (register.getTransactionCompleted()) {
                register.printReceipt();
            } else {
                LOG.error("Unable to complete customer transaction for " + nextCustomer.getName());
            }

            customerLine.getNextCustomer();
        }
    }
}
