import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Address[] addresses = Address.predefinedAddresses();
        Pharmacist[] pharmacists = Pharmacist.predefinedPharmacist();
        PharmacyTechnician[] technicians = PharmacyTechnician.predefinedPharmacyTechnicians();
        Pharmacy pharmacy = new Pharmacy("Joffrey's Pharmacy", addresses[0], "123-321-4567",
            "pharmEmail@email.com");
        pharmacy.hireEmployee(pharmacists[0]);
        pharmacy.hireEmployee(pharmacists[1]);
        pharmacy.hireEmployee((technicians[0]));
        pharmacy.hireEmployee((technicians[1]));
        pharmacy.printPharmacyInformation();
//
//        // Create products
//        Item product1 = new Item("Band-Aids", 2.99);
//        Item product2 = new Item("Hydrogen Peroxide", 1.99);
//        Item product3 = new Item("Antacid Tablets", 4.99);
//        Item product4 = new Item("Cough Drops", 3.49);
//        Medication medication1 = new Medication("Aspirin", "500 mg", 0.10);
//        Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08);
//        Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05);
//        Medication medication4 = new Medication("Naproxen", "220 mg", 0.12);
//
//        // Add to inventory
//        ProductInventory inventory = new ProductInventory();
//        inventory.addProduct(product1, 50);
//        inventory.addProduct(product2, 25);
//        inventory.addProduct(product3, 30);
//        inventory.addProduct(product4, 40);
//        inventory.addProduct(medication1, 100);
//        inventory.addProduct(medication2, 50);
//        inventory.addProduct(medication3, 200);
//        inventory.addProduct(medication4, 75);
//
//        Insurance insurance1 = new Insurance("MediCare", "123456", 80.0);
//        Insurance insurance2 = new Insurance("Blue Cross", "123456", 70.0);
//
//        Patient patient1 = new Patient("Tom Davis", "555-7890", addresses[5], insurance1);
//        Patient patient2 = new Patient("Sara Johnson", "555-2345", addresses[6], insurance2);
//        Patient patient3 = new Patient("Sara Johnson", "555-2345", addresses[6]);
//        System.out.println(patient3);
//        Prescription prescription1 = new Prescription("RX123", 2, false, medication1, patient1, 35);
//
//        Prescription prescription2 = new Prescription("RX456", 1, true, medication2, patient2);
//        Customer customerToPatient = new Consumer("customer", "123456789", addresses[0]);
//
//        pharmacy.setPrescriptionRegistry(new PrescriptionRegistry());
//        // patient 4
//        customerToPatient.providePrescription(pharmacy, prescription1);
//        // patient 5
//        Patient patient5 = new Patient("patient5", "555-2345", addresses[6]);
//        System.out.println(patient5.getPatientID()); // patientId-5
//        System.out.println(patient5); // patientId-5
//        System.out.println(Patient.getNumberOfPatients());// patientId-5
    }
}
