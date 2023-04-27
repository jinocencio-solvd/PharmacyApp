import exceptions.DuplicatePersonException;
import inventory.*;
import misc.Address;
import misc.Insurance;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import product.Item;
import product.Medication;
import product.Prescription;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    private static final Address[] ADDRESSES = Address.predefinedAddresses();
    private static final Pharmacist[] PHARMACISTS = Pharmacist.predefinedPharmacist();
    private static final PharmacyTechnician[] TECHNICIANS = PharmacyTechnician.predefinedPharmacyTechnicians();
    private static final Pharmacy PHARMACY = new Pharmacy("PharmacyRx",
        ADDRESSES[0], "123-321-4567", "PHARMEMAIL@EMAIL.COM");
    private static final Prescription[] PRESCRIPTIONS = Prescription.predefinedPrescriptions();
    private static final Medication[] MEDICATIONS = Medication.predefinedMedications();
    private static final Patient[] PATIENTS = Patient.predefinedPatients();
    private static final Insurance[] INSURANCES = Insurance.predefinedInsurance();
    private static final ProductInventory PRODUCT_INVENTORY = new ProductInventory();

    //TODO: implement predefined items
    private static final Item[] items = null;


    public static void hirePharmacyEmployees() throws DuplicatePersonException {
        LOG.info("Start hiring employees");
        PHARMACY.hireEmployee(PHARMACISTS[0]);
        PHARMACY.hireEmployee(PHARMACISTS[1]);
        PHARMACY.hireEmployee((TECHNICIANS[0]));
        PHARMACY.hireEmployee((TECHNICIANS[0]));
        PHARMACY.hireEmployee((TECHNICIANS[1]));
        LOG.info("Completed hiring employees");
    }

    public static ProductInventory populateInventory() {
        LOG.info("Start populating product inventory");
        // Create products
        Item product1 = new Item("Band-Aids", 2.99);
        Item product2 = new Item("Hydrogen Peroxide", 1.99);
        Item product3 = new Item("Antacid Tablets", 4.99);
        Item product4 = new Item("Cough Drops", 3.49);
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
    private static void userCreatePatient() {
        LOG.trace("In userCreatePatient");
        System.out.println("Welcome to " + PHARMACY.getName());
        Scanner s = new Scanner(System.in);
        String name = "";
        try {
            System.out.println("Enter patient name:");
            name = s.nextLine();
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            Patient userPatient = new Patient(name, "phoneNumber", Address.predefinedAddresses()[0],
                null);

            LOG.info("Patient " + userPatient.getName() + " created with patientId: "
                + userPatient.getPatientID());
            System.out.println("Thank you see you later.");
        } catch (IllegalArgumentException e) {
            LOG.warn("Name cannot be empty");
            userCreatePatient();
        } finally {
            s.close();
        }
    }

    public static void main(String[] args) throws DuplicatePersonException {
        // Pharmacy Operations
        hirePharmacyEmployees();
        ProductInventory productInventory = populateInventory();
        PHARMACY.setInventory(productInventory);
        PHARMACY.releaseEmployee(TECHNICIANS[1]);
        PHARMACY.releaseEmployee(TECHNICIANS[1]);

        userCreatePatient();
    }
}
