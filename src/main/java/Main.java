import customLambdaFunctions.IPerformOperation;
import customLambdaFunctions.IRepeater;
import enums.BusinessDay;
import fileReadWriter.FileReadWriter;
import genericLinkedList.CustomerLine;
import inventory.Cart;
import inventory.ProductInventory;
import java.time.DayOfWeek;
import java.util.Scanner;
import java.util.function.Consumer;
import misc.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Customer;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;
import product.Item;
import product.Medication;
import register.Receipt;
import register.Register;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final Pharmacist[] PHARMACISTS = DataProvider.predefinedPharmacist();
    private static final PharmacyTechnician[] TECHNICIANS = DataProvider.predefinedPharmacyTechnicians();
    private static final Patient[] PATIENTS = DataProvider.PATIENTS;
    private static final Customer[] CUSTOMERS = DataProvider.predefinedConsumers();
    private static Pharmacy pharmacy;

    private static void performPharmacyOperation(IPerformOperation<Pharmacy> operation) {
        operation.perform(pharmacy);
    }

    public static void setPharmacy(Pharmacy pharmacy) {
        Main.pharmacy = pharmacy;
    }

    private static final IPerformOperation<Pharmacy> hirePharmacyEmployees = (Pharmacy pharmacy) -> {
        LOG.info("Start hiring employees");
        pharmacy.hireEmployee(PHARMACISTS[0]);
        pharmacy.hireEmployee(PHARMACISTS[1]);
        pharmacy.hireEmployee((TECHNICIANS[0]));
        pharmacy.hireEmployee((TECHNICIANS[0])); // hire duplicate
        pharmacy.hireEmployee((TECHNICIANS[1]));
        LOG.info("Completed hiring employees");
    };

    private static final IPerformOperation<Pharmacy> populateInventory = (Pharmacy pharmacy) -> {
        LOG.info("Start populating product inventory");
        ProductInventory productInventory = new ProductInventory();
        for (Item item : DataProvider.predefinedItems()) {
            productInventory.addProduct(item, 50);
        }
        for (Medication medication : DataProvider.predefinedMedications()) {
            productInventory.addProduct(medication, 150);
        }
        LOG.info("Completed populating product inventory");
        pharmacy.setInventory(productInventory);
    };

    private static Pharmacy userCreatePharmacy() {
        LOG.trace("In userCreatePatient");
        Pharmacy pharmacy = DataProvider.predefinedPharmacy();
        try (Scanner s = new Scanner(System.in)) {
            String name = "";
            System.out.println("Enter pharmacy name:");
            name = s.nextLine();
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            pharmacy.setName(name);
            LOG.info("Pharmacy " + pharmacy.getName() + " created.");
            return pharmacy;
        } catch (IllegalArgumentException e) {
            LOG.warn("Name cannot be empty");
            userCreatePharmacy();
        }
        return pharmacy;
    }

    private static void pharmacyOperations(Pharmacy pharmacy) {
        performPharmacyOperation(hirePharmacyEmployees);
        performPharmacyOperation(populateInventory);
        pharmacy.releaseEmployee(TECHNICIANS[1]);
        pharmacy.releaseEmployee(TECHNICIANS[1]); // not found
    }

    private static void pharmacyOperationDays() {
        for (DayOfWeek day : DayOfWeek.values()) {
            String dayStr = day.name();
            BusinessDay businessDay = BusinessDay.getBusinessDay(dayStr);
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
        for (Customer c : CUSTOMERS) {
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

    private static void prescriptionOperations() {
        // Patient actions
        Patient patient = PATIENTS[0];
        Prescription prescriptionForPatient = DataProvider.predefinedPrescriptions()[0];
        patient.providePrescription(pharmacy, prescriptionForPatient);

        // Pharmacists action
        Pharmacist pharmacist = DataProvider.predefinedPharmacist()[0];
        Consumer<Pharmacy> runPharmacistFillAllRxReq = (Pharmacy p) ->
            pharmacist.fulfillAllPrescriptionLogRequests(
                p.getFilledPrescriptions(), p.getPrescriptionRequestLog(), p.getInventory(),
                p.getPrescriptionRegistry());

        runPharmacistFillAllRxReq.accept(pharmacy);

        // Demo for Patient transaction
        Register register = new Register(TECHNICIANS[0]);
        Cart cart = new Cart();

        Consumer<Register> registerOperations = (Register r) -> {
            register.setCustomer(patient);
            register.setCart(cart);
            register.processPrescriptionAndAddMedicationsToCart(
                pharmacy.getFilledPrescriptions());
            register.scanAllProductsInCart();
            register.processTransaction();
            if (register.getTransactionCompleted()) {
                Receipt receipt = register.printReceipt();
                System.out.println(receipt.getContent());
            }
        };

        IRepeater<Patient> patientRepeater = ((numRepeats, operation) -> {
            for (int i = 0; i < numRepeats; i++) {
                operation.perform(patient);
            }
        });

        registerOperations.accept(register); // InsufficientFunds
        // Patient withdraws more funds
        LOG.info(
            "Patient balance for " + patient.getName() + " is " + patient.getCreditBalance());
        patientRepeater.repeat(10, AbstractCustomer::increaseCreditBalanceByOneHundred);
        LOG.info("Patient balance for " + patient.getName()
            + " after being supplied with more credit is " + patient.getCreditBalance());
        registerOperations.accept(register);

        // Demo for refill requests and denial
        patientRepeater.repeat(3, (p) -> {
            p.requestPrescriptionRefill(pharmacy, prescriptionForPatient);
            runPharmacistFillAllRxReq.accept(pharmacy);
            registerOperations.accept(register);
            // TODO: Handle logic for customer presenting with an empty cart. Currently processes empty cart as a transaction
        });
    }

    public static void main(String[] args) {
        boolean userCreateMode = false;
        Pharmacy pharmacy =
            userCreateMode ? userCreatePharmacy() : DataProvider.predefinedPharmacy();
        setPharmacy(pharmacy);
        pharmacyOperations(pharmacy);
        customerLineOperations();
        pharmacyOperationDays();
        prescriptionOperations();
    }
}
