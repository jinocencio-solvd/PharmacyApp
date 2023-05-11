package misc;

import inventory.Cart;
import person.Customer;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import product.Item;
import product.Medication;
import prescriptionRegistry.Prescription;


public class DataProvider {


    private static final Address[] addresses = predefinedAddresses();
    private static final Insurance[] insurances = predefinedInsurance();
    // TODO: implement getters for global variables;
    //  {BUG}: calling predefined methods instantiates new objects rather than referencing the
    //  objects created in this class. Temporary fix is setting global var to public
    public static final Patient[] PATIENTS = predefinedPatients();
    private static final Medication[] medications = predefinedMedications();

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
        return new Patient[]{
            new Patient("Tom Davis", "555-7890", addresses[5], insurances[0]),
            new Patient("Sara Johnson", "555-2345", addresses[6], insurances[1]),
            new Patient("Sara II Johnson", "555-2345", addresses[6])
        };
    }

    public static Customer[] predefinedConsumers() {
        return new Customer[]{
            new Customer("Todd Daver", "555-7890", addresses[5]),
            new Customer("Saul Johnson", "555-2345", addresses[6]),
            new Customer("Saul II Johnson", "555-2345", addresses[6])
        };
    }


    public static Prescription[] predefinedPrescriptions() {
        return new Prescription[]{
            new Prescription(2, false, medications[0], PATIENTS[0], 35),
            new Prescription(2, false, medications[1], PATIENTS[0], 50),
            new Prescription(2, false, medications[2], PATIENTS[1], 35)
        };
    }

    public static Pharmacist[] predefinedPharmacist() {
        return new Pharmacist[]{
            new Pharmacist("John Doe", "555-1234", addresses[1], "CA-12345"),
            new Pharmacist("Jane Smith", "555-5678", addresses[2], "NY-67890")};
    }

    public static PharmacyTechnician[] predefinedPharmacyTechnicians() {
        return new PharmacyTechnician[]{
            new PharmacyTechnician("John Tech", "555-1234", addresses[3],
                "CA123456"),
            new PharmacyTechnician("Jane Tech", "555-5678", addresses[4],
                "CA654321")
        };
    }

    public static Medication[] predefinedMedications() {
        return new Medication[]{
            new Medication("Insulin", "50 mg/dL", 30.0),
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

    public static Cart[] predefinedCarts() {
        Cart itemsOnly = new Cart();
        Cart itemsOnly2 = new Cart();
        Cart medicationsOnly = new Cart();
        Cart itemsAndMedications = new Cart();
        for (Item i : predefinedItems()) {
            itemsOnly.addProduct(i, 1);
            itemsOnly2.addProduct(i, 3);
            itemsAndMedications.addProduct(i, 1);
        }
        for (Medication i : predefinedMedications()) {
            medicationsOnly.addProduct(i, 1);
            itemsAndMedications.addProduct(i, 1);
        }
        return new Cart[]{itemsOnly, itemsOnly2, medicationsOnly, itemsAndMedications};
    }
}
