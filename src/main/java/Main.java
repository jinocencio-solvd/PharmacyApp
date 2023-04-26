public class Main {

    public static void main(String[] args) {
        Address[] addresses = Address.predefinedAddresses();
        Pharmacist[] pharmacists = Pharmacist.predefinedPharmacist();
        PharmacyTechnician[] technicians = PharmacyTechnician.predefinedPharmacyTechnicians();
        Pharmacy pharmacy = new Pharmacy("Joffrey's Pharmacy", addresses[0], "123-321-4567",
            "pharmEmail@email.com");
        pharmacy.setPrescriptionRegistry(new PrescriptionRegistry());
        PrescriptionRegistry prescriptionRegistry = pharmacy.getPrescriptionRegistry();
        ProductInventory inventory = new ProductInventory();
        Item product1 = new Item("Band-Aids", 2.99);
        Item product2 = new Item("Hydrogen Peroxide", 1.99);
        Item product3 = new Item("Antacid Tablets", 4.99);
        Item product4 = new Item("Cough Drops", 3.49);
        Medication medication1 = new Medication("Aspirin", "500 mg", 0.10);
        Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08);
        Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05);
        Medication medication4 = new Medication("Naproxen", "220 mg", 0.12);
        Insurance insurance1 = new Insurance("MediCare", "123456", 80.0);
        Insurance insurance2 = new Insurance("Blue Cross", "123456", 70.0);
        Patient patient1 = new Patient("Tom Davis", "555-7890", addresses[5], insurance1);
        Patient patient2 = new Patient("Sara Johnson", "555-2345", addresses[6], insurance2);
        Patient patient3 = new Patient("Sara Johnson", "555-2345", addresses[6]);
        Prescription prescription1 = new Prescription("rxId:1", 2, false, medication1, patient1,
            35);
        Prescription prescription2 = new Prescription("rxId:2", 2, false, medication2, patient1,
            50);
        Prescription prescription3 = new Prescription("rxId:3", 2, false, medication2, patient2,
            35);

        // pharmacy interface methods
        pharmacy.hireEmployee(pharmacists[0]);
        pharmacy.hireEmployee(pharmacists[1]);
        pharmacy.hireEmployee((technicians[0]));
        pharmacy.hireEmployee((technicians[1]));
        pharmacy.releaseEmployee((technicians[1]));
        pharmacy.printPharmacyInformation();

        // Employee interface methods
        pharmacists[0].printEmployeeDetails();
        pharmacists[0].clockIn();
        pharmacists[0].clockOut();

        // inventory interface methods
        inventory.addProduct(product1, 50);
        inventory.addProduct(product2, 25);
        inventory.addProduct(product3, 30);
        inventory.addProduct(product4, 40);
        inventory.addProduct(medication1, 100);
        inventory.addProduct(medication2, 50);
        inventory.addProduct(medication3, 200);
        inventory.addProduct(medication4, 75);
        inventory.removeProduct(product4, 20);
        System.out.println(inventory.getQuantity(product4));        // 20
        System.out.println(inventory.getQuantity(medication2));     // 25

        // Customer interface methods / PrescriptionRegistry
        Customer customerToPatient = new Consumer("customer", "123456789", addresses[0]);

        // provision of prescription by customer creates new patient --> patient 4
        // .providePrescription uses pharmacy to access PrescriptionRegistry
        customerToPatient.providePrescription(pharmacy, prescription1);

        // patient 5
        Patient patient5 = new Patient("patient5", "555-2345", addresses[6]);
        System.out.println(patient5.getPatientID()); // patientId-5
        System.out.println(patient5); // patientId-5
        System.out.println(Patient.getNumberOfPatients());// patientId-5
    }
}
