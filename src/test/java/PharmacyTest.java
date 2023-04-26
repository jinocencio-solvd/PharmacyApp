import Exceptions.DuplicatePersonException;
import Inventory.Inventory;
import Misc.Address;
import Person.Employee;
import Person.Pharmacist;
import Person.PharmacyTechnician;
import Pharmacy.Pharmacy;
import Product.Item;
import Product.Medication;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PharmacyTest {

    Address address0 = new Address("001 Pearl St", "Anytown", "CA", 12345);
    Address address1 = new Address("123 Main St", "Anytown", "CA", 12345);
    Address address2 = new Address("456 Elm St", "Anytown", "NY", 67890);
    Address address3 = new Address("789 Oak St", "Sometown", "FL", 24680);
    Address address4 = new Address("321 Pine St", "Othertown", "TX", 13579);
    // Create products
    Item product1 = new Item("Band-Aids", 2.99);
    Item product2 = new Item("Hydrogen Peroxide", 1.99);
    Item product3 = new Item("Antacid Tablets", 4.99);
    Item product4 = new Item("Cough Drops", 3.49);
    Medication medication1 = new Medication("Aspirin", "500 mg", 0.10);
    Medication medication2 = new Medication("Ibuprofen", "200 mg", 0.08);
    Medication medication3 = new Medication("Acetaminophen", "325 mg", 0.05);
    Medication medication4 = new Medication("Naproxen", "220 mg", 0.12);
    ArrayList<Employee> employees = new ArrayList<>();
    Inventory inventory = new Inventory();
    Pharmacist pharmacist1;
    Pharmacist pharmacist2;
    PharmacyTechnician tech2;
    PharmacyTechnician tech1;
    Pharmacy pharmacy;
    Pharmacy pharmacyCopy;

    @BeforeEach
    void setUp() throws DuplicatePersonException {
        pharmacist1 = new Pharmacist("John Doe", "555-1234", address1, "CA-12345");
        pharmacist2 = new Pharmacist("Jane Smith", "555-5678", address2, "NY-67890");
        tech1 = new PharmacyTechnician("John Tech", "555-1234", address3, "CA123456");
        tech1.setCashierTrained(true);
        tech2 = new PharmacyTechnician("Jane Tech", "555-5678", address4, "CA654321");

        employees.add(pharmacist1);
        employees.add(pharmacist2);
        employees.add(tech1);
        employees.add(tech2);

        // Add to inventory
        inventory.addProduct(product1, 50);
        inventory.addProduct(product2, 25);
        inventory.addProduct(product3, 30);
        inventory.addProduct(product4, 40);
        inventory.addProduct(medication1, 100);
        inventory.addProduct(medication2, 50);
        inventory.addProduct(medication3, 200);
        inventory.addProduct(medication4, 75);

        pharmacy = new Pharmacy("Joffrey's Pharmacy.Pharmacy", address0, "123" + "-321-4567",
            "pharmEmail@email.com", inventory, employees);

        // Copy created using overloaded constructor
        pharmacyCopy = new Pharmacy("Joffrey's Pharmacy.Pharmacy", address0, "123" + "-321-4567",
            "pharmEmail@email.com");
        pharmacyCopy.hireEmployee(pharmacist1);
        pharmacyCopy.hireEmployee(pharmacist2);
        pharmacyCopy.hireEmployee(tech1);
        pharmacyCopy.hireEmployee(tech2);
        pharmacyCopy.setInventory(inventory);
    }

    @Test
    void testEquals() {
        assertEquals(pharmacy, pharmacyCopy);
    }

    @Test
    void testHashCode() {
        assertEquals(pharmacy.hashCode(), pharmacyCopy.hashCode());
    }
}