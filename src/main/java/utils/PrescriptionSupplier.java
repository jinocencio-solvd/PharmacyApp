package utils;

import inventory.ProductInventory;
import java.util.Random;
import java.util.function.Supplier;
import prescriptionRegistry.Prescription;
import product.Medication;

public class PrescriptionSupplier implements Supplier<Prescription> {

    private static final int MAX_REFILLS = 2;
    private static final int MAX_QUANTITY = 30;

    private final ProductInventory productInventory;

    public PrescriptionSupplier(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    @Override
    public Prescription get() {
        Medication randomMedication = (Medication) productInventory.getRandomProduct(
            Medication.class);
        int numRefills = new Random().nextInt(MAX_REFILLS);
        int numQuantity = new Random().nextInt(MAX_QUANTITY);
        return new Prescription(randomMedication, numQuantity, numRefills);
    }
}