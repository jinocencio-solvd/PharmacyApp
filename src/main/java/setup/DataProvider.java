package setup;

import enums.ProductType;
import inventory.Cart;
import misc.Address;
import misc.Insurance;
import person.Customer;
import person.Patient;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;
import product.Item;
import product.Medication;


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
            new Insurance("Blue Cross", 70.0),
            new Insurance("Aetna", 75.0),
            new Insurance("Cigna", 65.0),
            new Insurance("UnitedHealthcare", 85.0),
            new Insurance("Anthem", 60.0),
            new Insurance("Humana", 75.0),
            new Insurance("Kaiser Permanente", 90.0),
            new Insurance("Molina Healthcare", 70.0)
        };
    }

    public static Patient[] predefinedPatients() {
        return new Patient[]{
            new Patient("Tom Davis", "555-7890", addresses[5], insurances[7]),
            new Patient("Sara Johnson", "555-2345", addresses[6], insurances[1]),
            new Patient("Sara II Johnson", "555-2345", addresses[6], insurances[1]),
            new Patient("John Smith", "555-1234", addresses[0], insurances[2]),
            new Patient("Jane Doe", "555-5678", addresses[1], insurances[3]),
            new Patient("Emily Davis", "555-2222", addresses[3], insurances[5]),
            new Patient("Daniel Johnson", "555-3333", addresses[4], insurances[6]),
            new Patient("Olivia Wilson", "555-4444", addresses[5], insurances[7]),
            new Patient("William Lee", "555-5555", addresses[6], insurances[8]),
            new Patient("Sophia Moore", "555-6666", addresses[0], insurances[1]),
            new Patient("James Anderson", "555-7777", addresses[1], insurances[0]),
            new Patient("Isabella Thompson", "555-8888", addresses[2], insurances[1]),
            new Patient("Benjamin Martinez", "555-9999", addresses[3], insurances[2]),
            new Patient("Mia Garcia", "555-0000", addresses[4], insurances[3]),
            new Patient("Liam Johnson", "555-1111", addresses[5], insurances[4]),
            new Patient("Emma Wilson", "555-2222", addresses[6], insurances[5]),
            new Patient("Alexander Brown", "555-3333", addresses[0], insurances[6]),
            new Patient("Ava Davis", "555-4444", addresses[1], insurances[7]),
            new Patient("Lucas Smith", "555-5555", addresses[2], insurances[8])
        };
    }

    public static Customer[] predefinedCustomers() {
        return new Customer[]{
            new Customer("Todd Daver", "555-7890", addresses[5]),
            new Customer("Saul Johnson", "555-2345", addresses[6]),
            new Customer("Saul II Johnson", "555-2345", addresses[6]),
            new Customer("John Smith", "555-1234", addresses[0]),
            new Customer("Jane Doe", "555-5678", addresses[1]),
            new Customer("Michael Brown", "555-1111", addresses[2]),
            new Customer("Emily Davis", "555-2222", addresses[3]),
            new Customer("Daniel Johnson", "555-3333", addresses[4]),
            new Customer("Olivia Wilson", "555-4444", addresses[5]),
            new Customer("William Lee", "555-5555", addresses[6]),
            new Customer("Sophia Moore", "555-6666", addresses[0]),
            new Customer("James Anderson", "555-7777", addresses[1]),
            new Customer("Isabella Thompson", "555-8888", addresses[2]),
            new Customer("Benjamin Martinez", "555-9999", addresses[3]),
            new Customer("Mia Garcia", "555-0000", addresses[4])
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
            new Medication("Metformin", "500 mg twice daily", 15.0),
            new Medication("Levothyroxine", "50 mcg once daily", 20.0),
            new Medication("Lisinopril", "10 mg once daily", 25.0),
            new Medication("Atorvastatin", "20 mg once daily", 30.0),
            new Medication("Metoprolol", "50 mg twice daily", 20.0),
            new Medication("Omeprazole", "20 mg once daily", 15.0),
            new Medication("Warfarin", "2.5 mg once daily", 35.0),
            new Medication("Albuterol", "1-2 puffs every 4-6 hours as needed for asthma", 25.0),
            new Medication("Fluoxetine", "20 mg once daily", 30.0)
        };
    }

    public static Item[] predefinedItems() {
        return new Item[]{
            new Item("Band-Aids", 2.99, ProductType.FIRST_AID, "BA001"),
            new Item("Hydrogen Peroxide", 1.99, ProductType.FIRST_AID, "HP001"),
            new Item("Antacid Tablets", 4.99, ProductType.OVER_THE_COUNTER_MEDICINE, "AT001"),
            new Item("Cough Drops", 3.49, ProductType.OVER_THE_COUNTER_MEDICINE, "CD001"),
            new Item("Sunscreen Lotion", 7.99, ProductType.FIRST_AID, "SL001"),
            new Item("Insect Repellent", 5.99, ProductType.FIRST_AID, "IR001"),
            new Item("Hand Sanitizer", 2.49, ProductType.FIRST_AID, "HS001"),
            new Item("Multivitamin Tablets", 9.99, ProductType.VITAMINS_SUPPLEMENTS, "MT001"),
            new Item("Calcium Supplements", 12.99, ProductType.VITAMINS_SUPPLEMENTS, "CS001"),
            new Item("Fish Oil Supplements", 8.99, ProductType.VITAMINS_SUPPLEMENTS, "FOS001"),
            new Item("Protein Powder", 17.99, ProductType.VITAMINS_SUPPLEMENTS, "PP001"),
            new Item("Digital Thermometer", 12.49, ProductType.FIRST_AID, "DT001"),
            new Item("Nasal Spray", 6.99, ProductType.OVER_THE_COUNTER_MEDICINE, "NS001"),
            new Item("Muscle Rub Cream", 8.99, ProductType.FIRST_AID, "MRC001"),
            new Item("Ear Wax Removal Drops", 5.99, ProductType.OVER_THE_COUNTER_MEDICINE, "EW001"),
            new Item("Contact Lens Solution", 10.99, ProductType.UNLABELED, "CLS001"),
            new Item("Sleeping Aid Tablets", 7.99, ProductType.OVER_THE_COUNTER_MEDICINE, "SAT001"),
            new Item("Digestive Enzymes Supplements", 14.99, ProductType.VITAMINS_SUPPLEMENTS,
                "DES001"),
            new Item("Acetaminophen Tablets", 3.99, ProductType.OVER_THE_COUNTER_MEDICINE,
                "ACT001"),
            new Item("Ibuprofen Tablets", 4.99, ProductType.OVER_THE_COUNTER_MEDICINE, "IBU001"),
            new Item("Antihistamine Tablets", 6.99, ProductType.OVER_THE_COUNTER_MEDICINE, "AH001"),
            new Item("Anti-Diarrheal Tablets", 5.99, ProductType.OVER_THE_COUNTER_MEDICINE,
                "ADT001"),
            new Item("Cold and Flu Relief Syrup", 8.99, ProductType.OVER_THE_COUNTER_MEDICINE,
                "CFS001")
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
