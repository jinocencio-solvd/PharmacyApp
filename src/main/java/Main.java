import Exceptions.DuplicatePersonException;
import Inventory.*;
import Misc.Address;
import Misc.Insurance;
import Person.Consumer;
import Person.Customer;
import Person.Patient;
import Person.Pharmacist;
import Person.PharmacyTechnician;
import Pharmacy.Pharmacy;
import Product.Item;
import Product.Medication;
import Product.Prescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    private static final Address[] ADDRESSES = Address.predefinedAddresses();
    private static final Pharmacist[] PHARMACISTS = Pharmacist.predefinedPharmacist();
    private static final PharmacyTechnician[] TECHNICIANS = PharmacyTechnician.predefinedPharmacyTechnicians();
    private static final Pharmacy PHARMACY = new Pharmacy("Joffrey's Pharmacy.Pharmacy",
        ADDRESSES[0], "123-321-4567", "PHARMEMAIL@EMAIL.COM");
    private static final Prescription[] PRESCRIPTIONS = Prescription.predefinedPrescriptions();
    private static final Medication[] MEDICATIONS = Medication.predefinedMedications();
    private static final Patient[] PATIENTS = Patient.predefinedPatients();
    private static final Insurance[] INSURANCES = Insurance.predefinedInsurance();
    private static final ProductInventory PRODUCT_INVENTORY  = new ProductInventory();

    //TODO: implement predefined items
    private static final Item[] items = null;


    public static void hirePharmacyEmployees() {
        try {
            LOG.info("Start hiring employees");
            PHARMACY.hireEmployee(PHARMACISTS[0]);
            PHARMACY.hireEmployee(PHARMACISTS[1]);
            PHARMACY.hireEmployee((TECHNICIANS[0]));
            PHARMACY.hireEmployee((TECHNICIANS[1]));
            PHARMACY.hireEmployee((TECHNICIANS[1]));
            LOG.info("Completed hiring employees");
        } catch (DuplicatePersonException e) {
            LOG.warn("One of the employees is already in system");
        }
    }

    public static ProductInventory populateInventory(){
        LOG.info("Start populating product inventory");
        // Create products
        Item product1 = new Product.Item("Band-Aids", 2.99);
        Item product2 = new Product.Item("Hydrogen Peroxide", 1.99);
        Item product3 = new Product.Item("Antacid Tablets", 4.99);
        Item product4 = new Product.Item("Cough Drops", 3.49);
        // Add to inventory
        PRODUCT_INVENTORY.addProduct(product1, 50);
        PRODUCT_INVENTORY.addProduct(product2, 25);
        PRODUCT_INVENTORY.addProduct(product3, 30);
        PRODUCT_INVENTORY.addProduct(product4, 40);
        PRODUCT_INVENTORY.addProduct(MEDICATIONS[0], 100);
        PRODUCT_INVENTORY.addProduct(MEDICATIONS[1], 50);
        PRODUCT_INVENTORY.addProduct(MEDICATIONS[2], 200);
        PRODUCT_INVENTORY.addProduct(MEDICATIONS[3], 75);
        LOG.info("Completed populating product inventory");
        return PRODUCT_INVENTORY;
    }

    public static void main(String[] args) {
        hirePharmacyEmployees();
        ProductInventory productInventory =  populateInventory();
        PHARMACY.setInventory(productInventory);
    }
}
