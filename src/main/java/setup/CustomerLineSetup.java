package setup;

import genericLinkedList.CustomerLine;
import inventory.Cart;
import inventory.ProductInventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Customer;
import person.Patient;
import pharmacy.Pharmacy;

public class CustomerLineSetup {

    private static final Logger LOG = LogManager.getLogger(CustomerLineSetup.class);
    private static final Patient[] PATIENTS = DataProvider.PATIENTS;
    private static final Customer[] CUSTOMERS = DataProvider.predefinedCustomers();
    private static final int MAX_NUMBER_ITEMS = 15;

    private CustomerLine customerLine;
    private Pharmacy pharmacy;
    private int numCustomers;
    private ProductInventory productInventory;
    CartSupplier cartSupplier;

    public CustomerLineSetup(Pharmacy pharmacy, int numCustomers) {
        this.customerLine = new CustomerLine();
        this.pharmacy = pharmacy;
        productInventory = pharmacy.getProductInventory();
        cartSupplier = new CartSupplier(productInventory, MAX_NUMBER_ITEMS);
    }

    private void populateLineWithCustomers() {
        for (Customer c : CUSTOMERS) {
            Cart cart = cartSupplier.get();
            c.setCart(cart);
            customerLine.addCustomer(c);
        }
    }

    private void populateLineWithPatients() {
        for (Patient p : PATIENTS) {
            Cart cart = cartSupplier.get();
            p.setCart(cart);
            customerLine.addCustomer(p);
        }
    }

    public CustomerLine setup() {
        populateLineWithCustomers();
        populateLineWithPatients();
        return customerLine;
    }
}
