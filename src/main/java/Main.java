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

    PharmacyTechnician tech1 = new PharmacyTechnician("John Tech", "555-1234", address3, "EMP-001",
        "CA123456");
    tech1.setCashierTrained(true);
    PharmacyTechnician tech2 = new PharmacyTechnician("Jane Tech", "555-5678", address4, "EMP-002",
        "CA654321");

    ArrayList<Employee> employees = new ArrayList<>();
    employees.add(pharmacist1);
    employees.add(pharmacist2);
    employees.add(tech1);
    employees.add(tech2);

    // Create products
    Item product1 = new Item("Band-Aids", 2.99, 50);
    Item product2 = new Item("Hydrogen Peroxide", 1.99, 25);
    Item product3 = new Item("Antacid Tablets", 4.99, 30);
    Item product4 = new Item("Cough Drops", 3.49, 40);
    Medication medication1 = new Medication("Aspirin", "500 mg", 0.10, 100);
    Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08, 50);
    Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05, 200);
    Medication medication4 = new Medication("Naproxen", "220 mg", 0.12, 75);

    // Add to inventory
    Inventory inventory = new Inventory();
    inventory.addProductToInventory(product1);
    inventory.addProductToInventory(product2);
    inventory.addProductToInventory(product3);
    inventory.addProductToInventory(product4);
    inventory.addProductToInventory(medication1);
    inventory.addProductToInventory(medication2);
    inventory.addProductToInventory(medication3);
    inventory.addProductToInventory(medication4);

    Pharmacy pharmacy = new Pharmacy("Joffrey's Pharmacy", address0, "123" + "-321-4567",
        "pharmEmail@email.com", inventory, employees);

    Patient patient1 = new Patient("Tom Davis", "555-7890", address5, 1005, "Humana");
    Patient patient2 = new Patient("Sara Johnson", "555-2345", address6, 1006, "Anthem");

    Prescription prescription1 = new Prescription("RX123", 2, false, medication1, patient1);

    Prescription prescription2 = new Prescription("RX456", 1, true, medication2, patient2);

//    pharmacist1.printEmployeeDetails();
//    tech1.printEmployeeDetails();
  }
}
