package setup;

import customLambdaFunctions.IPerformOperation;
import enums.BusinessDay;
import inventory.ProductInventory;
import java.time.DayOfWeek;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Pharmacist;
import person.PharmacyTechnician;
import pharmacy.Pharmacy;
import product.Item;
import product.Medication;

public class PharmacySetup {

    private static final Logger LOG = LogManager.getLogger(PharmacySetup.class);

    private static final Pharmacy pharmacy = DataProvider.predefinedPharmacy();
    private static final PharmacyTechnician[] TECHNICIANS = DataProvider.predefinedPharmacyTechnicians();
    private static final Pharmacist[] PHARMACISTS = DataProvider.predefinedPharmacist();
    private static final int MAX_NUM_PER_ITEMS = 500;
    private static final int MAX_NUM_PER_MEDICATION = 500;

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
            productInventory.addProduct(item, MAX_NUM_PER_ITEMS);
        }
        for (Medication medication : DataProvider.predefinedMedications()) {
            productInventory.addProduct(medication, MAX_NUM_PER_MEDICATION);
        }
        LOG.info("Completed populating product inventory");
        pharmacy.setProductInventory(productInventory);
    };

    private static void performPharmacyOperation(IPerformOperation<Pharmacy> operation) {
        operation.perform(pharmacy);
    }

    private static void pharmacyOperations() {
        performPharmacyOperation(hirePharmacyEmployees);
        performPharmacyOperation(populateInventory);
        pharmacy.releaseEmployee(TECHNICIANS[1]);
        pharmacy.releaseEmployee(TECHNICIANS[1]); // not found
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

    public static Pharmacy setup() {
        pharmacyOperations();
        pharmacyOperationDays();
        return pharmacy;
    }

    public static Pharmacy setup(Pharmacy pharmacy) {
        pharmacyOperations(pharmacy);
        pharmacyOperationDays();
        return pharmacy;
    }
}
