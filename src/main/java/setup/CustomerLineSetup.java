package setup;

import genericLinkedList.CustomerLine;
import inventory.Cart;
import inventory.ProductInventory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Customer;
import person.Patient;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;

public class CustomerLineSetup {

    private static final Logger LOG = LogManager.getLogger(CustomerLineSetup.class);
    private static final Patient[] PATIENTS = DataProvider.PATIENTS;
    private static final Customer[] CUSTOMERS = DataProvider.predefinedCustomers();
    private static final List<AbstractCustomer> removedCustomers = new ArrayList<>();
    private static final int MAX_NUMBER_ITEMS = 15;
    private static final int CUSTOMER_MAX_BALANCE = 1000;

    private CustomerLine customerLine;
    private int numCustomers;
    private ProductInventory productInventory;
    private CartSupplier cartSupplier;
    private Pharmacy pharmacy;

    public CustomerLineSetup(Pharmacy pharmacy, int numCustomers) {
        this.customerLine = new CustomerLine();
        this.numCustomers = numCustomers;
        this.pharmacy = pharmacy;
        productInventory = pharmacy.getProductInventory();
        cartSupplier = new CartSupplier(productInventory, MAX_NUMBER_ITEMS);
    }

    private void populateLineWithCustomers() {
        for (Customer c : CUSTOMERS) {
            Cart cart = cartSupplier.get();
            c.setCart(cart);
            c.setCreditBalance(CUSTOMER_MAX_BALANCE);
            customerLine.addCustomer(c);
        }
    }

    public static List<AbstractCustomer> getRemovedCustomers(){
        return removedCustomers;
    }

    private void populateLineWithPatients() {
        PrescriptionSupplier prescriptionSupplier = new PrescriptionSupplier(productInventory);
        for (Patient p : PATIENTS) {
            Cart cart = cartSupplier.get();
            Prescription prescription = prescriptionSupplier.get();
            prescription.setPatient(p);
            p.setCart(cart);
            p.setCreditBalance(CUSTOMER_MAX_BALANCE);
            p.providePrescription(pharmacy, prescription);

            customerLine.addCustomer(p);
        }
    }


    public CustomerLine setup() {
        populateLineWithCustomers();
        populateLineWithPatients();

        while (customerLine.size() > numCustomers) {
            int randIndex = new Random().nextInt(customerLine.size());
            AbstractCustomer removedCustomer = customerLine.get(randIndex);
            customerLine.removeAt(randIndex);
            LOG.trace(removedCustomer.getName() + " left the line to do more shopping.");
            removedCustomers.add(removedCustomer);
        }
        return customerLine;
    }
}
