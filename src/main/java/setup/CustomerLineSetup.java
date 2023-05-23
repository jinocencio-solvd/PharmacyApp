package setup;

import static setup.AppConfig.NUM_PATIENTS;

import inventory.Cart;
import inventory.ProductInventory;
import java.util.ArrayList;
import java.util.List;
import misc.ConcurrentCustomerLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Customer;
import person.Patient;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;
import utils.CartSupplier;
import utils.CustomerSupplier;
import utils.PatientSupplier;
import utils.PrescriptionSupplier;

public class CustomerLineSetup {

    private static final Logger LOG = LogManager.getLogger(CustomerLineSetup.class);
    private static final List<AbstractCustomer> removedCustomers = new ArrayList<>();
    private static final int MAX_NUMBER_ITEMS = AppConfig.MAX_NUMBER_ITEMS_IN_CART;
    private static int count = 0;

    private ConcurrentCustomerLine customerLine;
    private int numCustomers;
    private ProductInventory productInventory;
    private CartSupplier cartSupplier;
    private Pharmacy pharmacy;

    public CustomerLineSetup(Pharmacy pharmacy, int numCustomers) {
        this.customerLine = new ConcurrentCustomerLine();
        this.numCustomers = numCustomers;
        this.pharmacy = pharmacy;
        productInventory = pharmacy.getProductInventory();
        cartSupplier = new CartSupplier(productInventory, MAX_NUMBER_ITEMS);
    }

    private void populateLineWithCustomer() {
        Customer c = new CustomerSupplier().get();
        Cart cart = cartSupplier.get();
        c.setCart(cart);
        c.setName("customer-" + count++);
        customerLine.addCustomer(c);
    }

    public static List<AbstractCustomer> getRemovedCustomers() {
        return removedCustomers;
    }

    private void populateLineWithPatient() {
        PrescriptionSupplier prescriptionSupplier = new PrescriptionSupplier(productInventory);
        Patient p = new PatientSupplier().get();
        Cart cart = cartSupplier.get();
        Prescription prescription = prescriptionSupplier.get();
        prescription.setPatient(p);
        p.setCart(cart);
        p.setName("customer-P" + count++);
        p.providePrescription(pharmacy, prescription);

        customerLine.addCustomer(p);
    }

    public ConcurrentCustomerLine setup() {
        for (int i = 0; i < NUM_PATIENTS; i++) {
            populateLineWithPatient();
        }
        while (customerLine.getLineLength() < numCustomers) {
            populateLineWithCustomer();
        }
        customerLine.getCustomerLineInformation();
        return customerLine;
    }
}
