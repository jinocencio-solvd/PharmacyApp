import fileReadWriter.FileReadWriter;
import genericLinkedList.CustomerLine;
import inventory.ProductInventory;
import java.time.DayOfWeek;
import java.util.Scanner;
import misc.BusinessDays;
import misc.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Consumer;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import product.Item;
import product.Medication;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final Pharmacist[] PHARMACISTS = DataProvider.predefinedPharmacist();
    private static final PharmacyTechnician[] TECHNICIANS = DataProvider.predefinedPharmacyTechnicians();
    private static final Patient[] PATIENTS = DataProvider.predefinedPatients();
    private static final Consumer[] CONSUMERS = DataProvider.predefinedConsumers();
    private static Pharmacy pharmacy;

    public static void hirePharmacyEmployees(Pharmacy pharmacy) {
        LOG.info("Start hiring employees");
        pharmacy.hireEmployee(PHARMACISTS[0]);
        pharmacy.hireEmployee(PHARMACISTS[1]);
        pharmacy.hireEmployee((TECHNICIANS[0]));
        pharmacy.hireEmployee((TECHNICIANS[0])); // hire duplicate
        pharmacy.hireEmployee((TECHNICIANS[1]));
        LOG.info("Completed hiring employees");
    }

    public static ProductInventory populateInventory() {
        LOG.info("Start populating product inventory");
        ProductInventory productInventory = new ProductInventory();
        for (Item item : DataProvider.predefinedItems()) {
            productInventory.addProduct(item, 50);
        }
        for (Medication medication : DataProvider.predefinedMedications()) {
            productInventory.addProduct(medication, 100);
        }
        LOG.info("Completed populating product inventory");
        return productInventory;
    }

    private static void userCreatePatient(Pharmacy pharmacy) {
        LOG.trace("In userCreatePatient");
        System.out.println("Welcome to " + pharmacy.getName());
        Scanner s = new Scanner(System.in);
        String name = "";
        try {
            System.out.println("Enter patient name:");
            name = s.nextLine();
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            Patient userPatient = new Patient(name, "phoneNumber",
                DataProvider.predefinedAddresses()[0],
                null);

            LOG.info("Patient " + userPatient.getName() + " created with patientId: "
                + userPatient.getPatientID());
            System.out.println("Thank you see you later.");
        } catch (IllegalArgumentException e) {
            LOG.warn("Name cannot be empty");
            userCreatePatient(pharmacy);
        } finally {
            s.close();
        }
    }

    private static void pharmacyOperations() {
        // Pharmacy Operations
        pharmacy = DataProvider.predefinedPharmacy();
        hirePharmacyEmployees(pharmacy);
        ProductInventory productInventory = populateInventory();
        pharmacy.setInventory(productInventory);
        pharmacy.releaseEmployee(TECHNICIANS[1]);
        pharmacy.releaseEmployee(TECHNICIANS[1]); // not found
    }

    private static void pharmacyOperationDays() {
        for (DayOfWeek day : DayOfWeek.values()) {
            String dayStr = day.name();
            BusinessDays businessDay = BusinessDays.getBusinessDay(dayStr);
            LOG.info(businessDay.getDescription());
            if (pharmacy.isOpen(day)) {
                LOG.info("Come on in!");
            } else {
                LOG.info("Come back later!");
            }
        }
    }

    private static void customerLineOperations() {
        CustomerLine customerLine = new CustomerLine();
        for (Patient p : PATIENTS) {
            customerLine.addCustomer(p);
        }
        for (Consumer c : CONSUMERS) {
            customerLine.addCustomer(c);
        }
        customerLine.getLineLength();
        customerLine.getNextCustomer();

        customerLine.getLineLength();
        customerLine.getNextCustomer();
        for (int i = 0; i < 10; i++) {
            customerLine.getNextCustomer();
        }
    }

    public static void runCountWords() {
        String filePath = "src/main/resources/PharmacyRxDescription.txt";
        FileReadWriter.runFileWriteWithUtils(filePath);
    }

    public static void main(String[] args) {
        pharmacyOperations();
        customerLineOperations();
        pharmacyOperationDays();
    }
}
