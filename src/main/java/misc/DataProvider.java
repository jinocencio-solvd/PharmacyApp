package misc;

import inventory.Cart;
import person.Consumer;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import product.Item;
import product.Medication;
import prescriptionRegistry.Prescription;


public class DataProvider {

    public static Address[] predefinedAddresses() {
        return new Address[]{
            new Address("001 Pearl St", "Anytown", "CA", 12345),
            new Address("123 Main St", "Anytown", "CA", 12345),
            new Address("456 Elm St", "Anytown", "NY", 67890),
            new Address("789 Oak St", "Sometown", "FL", 24680),
            new Address("321 Pine St", "Othertown", "TX", 13579),
            new Address("111 Maple St", "Another Town", "GA", 97531),
            new Address("222 Cedar St", "Someplace", "VA", 86420)
        };
    }

    public static Insurance[] predefinedInsurance() {
        return new Insurance[]{
            new Insurance("MediCare", 80.0),
            new Insurance("Blue Cross", 70.0)
        };
    }

    public static Patient[] predefinedPatients() {
        Address[] addresses = predefinedAddresses();
        Insurance[] insurances = predefinedInsurance();
        return new Patient[]{
            new Patient("Tom Davis", "555-7890", addresses[5], insurances[0]),
            new Patient("Sara Johnson", "555-2345", addresses[6], insurances[1]),
            new Patient("Sara II Johnson", "555-2345", addresses[6])
        };
    }

    public static Consumer[] predefinedConsumers() {
        Address[] addresses = predefinedAddresses();
        Insurance[] insurances = predefinedInsurance();
        return new Consumer[]{
            new Consumer("Todd Daver", "555-7890", addresses[5]),
            new Consumer("Saul Johnson", "555-2345", addresses[6]),
            new Consumer("Saul II Johnson", "555-2345", addresses[6])
        };
    }


    public static Prescription[] predefinedPrescriptions() {
        Patient[] patients = predefinedPatients();
        Medication[] medications = predefinedMedications();
        return new Prescription[]{
            new Prescription(2, false, medications[0], patients[0], 35),
            new Prescription(2, false, medications[1], patients[0], 50),
            new Prescription(2, false, medications[2], patients[1], 35)
        };
    }

    public static Pharmacist[] predefinedPharmacist() {
        Address[] addresses = predefinedAddresses();
        return new Pharmacist[]{
            new Pharmacist("John Doe", "555-1234", addresses[1], "CA-12345"),
            new Pharmacist("Jane Smith", "555-5678", addresses[2], "NY-67890")};
    }

    public static PharmacyTechnician[] predefinedPharmacyTechnicians() {
        Address[] addresses = DataProvider.predefinedAddresses();
        return new PharmacyTechnician[]{
            new PharmacyTechnician("John Tech", "555-1234", addresses[3],
                "CA123456"),
            new PharmacyTechnician("Jane Tech", "555-5678", addresses[4],
                "CA654321")
        };
    }

    public static Medication[] predefinedMedications() {
        return new Medication[]{
            new Medication("Aspirin", "500 mg", 0.10),
            new Medication("Ibuprofen", "200 mg", 0.08),
            new Medication("Acetaminophen", "325 mg", 0.05),
            new Medication("Naproxen", "220 mg", 0.12)
        };
    }

    public static Item[] predefinedItems() {
        return new Item[]{
            new Item("Band-Aids", 2.99),
            new Item("Hydrogen Peroxide", 1.99),
            new Item("Antacid Tablets", 4.99),
            new Item("Cough Drops", 3.49)
        };
    }

    public static Pharmacy predefinedPharmacy() {
        return new Pharmacy("PharmacyRx", predefinedAddresses()[0], "(123)-342-4344",
            "pharm@pharmaEmail.com");
    }

    public static Cart predefinedCartItems() {
        Cart cart = new Cart();
        for (Item i : predefinedItems()) {
            cart.addProduct(i, 1);
        }
        return cart;
    }
}
