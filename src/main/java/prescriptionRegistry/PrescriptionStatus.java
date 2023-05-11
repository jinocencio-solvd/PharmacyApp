package prescriptionRegistry;

public enum PrescriptionStatus {
    NEW(1, "Has been created, but not yet processed."),
    PENDING(2, "Awaiting approval from pharmacist."),
    REFILL_UPON_REQUEST(3, "Prescription in system, refillable"),
    FILLED(4, "Has been processed and is ready for pickup."),
    CANCELLED(5, "Canceled by healthcare provider or patient."),
    COMPLETED(6, "Prescription can no longer be refilled.");

    private int id;
    private String description;

    PrescriptionStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

}
