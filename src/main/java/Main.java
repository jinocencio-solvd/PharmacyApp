import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Address address0 = new Address("001 Pearl St", "Anytown", "CA", 12345);
        Address address1 = new Address("123 Main St", "Anytown", "CA", 12345);
        Address address2 = new Address("456 Elm St", "Anytown", "NY", 67890);
        Address address3 = new Address("789 Oak St", "Sometown", "FL", 24680);
        Address address4 = new Address("321 Pine St", "Othertown", "TX", 13579);
        Address address5 = new Address("111 Maple St", "Another Town", "GA",
            97531);
        Address address6 = new Address("222 Cedar St", "Someplace", "VA",
            86420);

        Pharmacist pharmacist1 = new Pharmacist("John Doe", "555-1234",
            address1, "E001", "CA-12345");
        Pharmacist pharmacist2 = new Pharmacist("Jane Smith", "555-5678",
            address2, "E002", "NY-67890");

        PharmacyTechnician tech1 = new PharmacyTechnician("John Tech", "555-1234", address3,
            "EMP-001",
            "CA123456");
        tech1.setCashierTrained(true);
        PharmacyTechnician tech2 = new PharmacyTechnician("Jane Tech", "555-5678", address4,
            "EMP-002",
            "CA654321");

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(pharmacist1);
        employees.add(pharmacist2);
        employees.add(tech1);
        employees.add(tech2);

        // Create products
        Item product1 = new Item("Band-Aids", 2.99);
        Item product2 = new Item("Hydrogen Peroxide", 1.99);
        Item product3 = new Item("Antacid Tablets", 4.99);
        Item product4 = new Item("Cough Drops", 3.49);
        Medication medication1 = new Medication("Aspirin", "500 mg", 0.10);
        Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08);
        Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05);
        Medication medication4 = new Medication("Naproxen", "220 mg", 0.12);

        // Add to inventory
        ProductInventory inventory = new ProductInventory();
        inventory.addProduct(product1, 50);
        inventory.addProduct(product2, 25);
        inventory.addProduct(product3, 30);
        inventory.addProduct(product4, 40);
        inventory.addProduct(medication1, 100);
        inventory.addProduct(medication2, 50);
        inventory.addProduct(medication3, 200);
        inventory.addProduct(medication4, 75);

        Pharmacy pharmacy = new Pharmacy("Joffrey's Pharmacy", address0, "123" + "-321-4567",
            "pharmEmail@email.com", inventory, employees);

        Pharmacy pharmacyCopy = new Pharmacy("Joffrey's Pharmacy", address0, "123" + "-321-4567",
            "pharmEmail@email.com");
        pharmacyCopy.hireEmployee(pharmacist1);
        pharmacyCopy.hireEmployee(pharmacist2);
        pharmacyCopy.printPharmacyInformation();
        Insurance insurance1 = new Insurance("MediCare", "123456", 80.0);
        Insurance insurance2 = new Insurance("Blue Cross", "123456", 70.0);

        Patient patient1 = new Patient("Tom Davis", "555-7890", address5,  insurance1);
        Patient patient2 = new Patient("Sara Johnson", "555-2345", address6, insurance2);
        Patient patient3 = new Patient("Sara Johnson", "555-2345", address6);
        System.out.println(patient3);
        Prescription prescription1 = new Prescription("RX123", 2, false, medication1, patient1, 35);

        Prescription prescription2 = new Prescription("RX456", 1, true, medication2, patient2);
        Customer customerToPatient = new Consumer("customer", "123456789", address0);

        pharmacy.setPrescriptionRegistry(new PrescriptionRegistry());
        // patient 4
        customerToPatient.providePrescription(pharmacy,prescription1);
        // patient 5
        Patient patient5 = new Patient("patient5", "555-2345", address6);
        System.out.println(patient5.getPatientID()); // patientId-5
        System.out.println(patient5); // patientId-5
        System.out.println(Patient.getNumberOfPatients());// patientId-5
    }
}
