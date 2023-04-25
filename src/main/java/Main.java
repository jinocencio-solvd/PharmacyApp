import Misc.Address;
import Person.Pharmacist;
import Person.PharmacyTechnician;
import Pharmacy.Pharmacy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Address[] addresses = Address.predefinedAddresses();
        Pharmacist[] pharmacists = Pharmacist.predefinedPharmacist();
        PharmacyTechnician[] technicians = PharmacyTechnician.predefinedPharmacyTechnicians();
        Pharmacy pharmacy = new Pharmacy("Joffrey's Pharmacy.Pharmacy", addresses[0],
            "123-321-4567",
            "pharmEmail@email.com");
        pharmacy.hireEmployee(pharmacists[0]);
        pharmacy.hireEmployee(pharmacists[1]);
        pharmacy.hireEmployee((technicians[0]));
        pharmacy.hireEmployee((technicians[1]));
//        pharmacy.printPharmacyInformation();
        LOG.debug("Debug Message Logged !!!");
        LOG.info("Info Message Logged !!!");
        LOG.error("Error Message Logged !!!", new NullPointerException("NullError"));

//        // Create products
//        Product.Item product1 = new Product.Item("Band-Aids", 2.99);
//        Product.Item product2 = new Product.Item("Hydrogen Peroxide", 1.99);
//        Product.Item product3 = new Product.Item("Antacid Tablets", 4.99);
//        Product.Item product4 = new Product.Item("Cough Drops", 3.49);
//        Product.Medication medication1 = new Product.Medication("Aspirin", "500 mg", 0.10);
//        Product.Medication medication2 = new Product.Medication("Ibuprofen", "200 mg", 0.08);
//        Product.Medication medication3 = new Product.Medication("Acetaminophen", "325 mg", 0.05);
//        Product.Medication medication4 = new Product.Medication("Naproxen", "220 mg", 0.12);
//
//        // Add to inventory
//        Inventory.ProductInventory inventory = new Inventory.ProductInventory();
//        inventory.addProduct(product1, 50);
//        inventory.addProduct(product2, 25);
//        inventory.addProduct(product3, 30);
//        inventory.addProduct(product4, 40);
//        inventory.addProduct(medication1, 100);
//        inventory.addProduct(medication2, 50);
//        inventory.addProduct(medication3, 200);
//        inventory.addProduct(medication4, 75);
//
//        Misc.Insurance insurance1 = new Misc.Insurance("MediCare", "123456", 80.0);
//        Misc.Insurance insurance2 = new Misc.Insurance("Blue Cross", "123456", 70.0);
//
//        Person.Patient patient1 = new Person.Patient("Tom Davis", "555-7890", addresses[5], insurance1);
//        Person.Patient patient2 = new Person.Patient("Sara Johnson", "555-2345", addresses[6], insurance2);
//        Person.Patient patient3 = new Person.Patient("Sara Johnson", "555-2345", addresses[6]);
//        System.out.println(patient3);
//        Product.Prescription prescription1 = new Product.Prescription("RX123", 2, false, medication1, patient1, 35);
//
//        Product.Prescription prescription2 = new Product.Prescription("RX456", 1, true, medication2, patient2);
//        Person.Customer customerToPatient = new Person.Consumer("customer", "123456789", addresses[0]);
//
//        pharmacy.setPrescriptionRegistry(new PrescriptionRegistry.PrescriptionRegistry());
//        // patient 4
//        customerToPatient.providePrescription(pharmacy, prescription1);
//        // patient 5
//        Person.Patient patient5 = new Person.Patient("patient5", "555-2345", addresses[6]);
//        System.out.println(patient5.getPatientID()); // patientId-5
//        System.out.println(patient5); // patientId-5
//        System.out.println(Person.Patient.getNumberOfPatients());// patientId-5
    }
}
