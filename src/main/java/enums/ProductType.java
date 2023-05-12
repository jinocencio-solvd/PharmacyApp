package enums;

// TODO: Organize receipt display by ProductType, Display description as label
public enum ProductType {
    OVER_THE_COUNTER_MEDICINE(0, "OTC Medicine"),
    VITAMINS_SUPPLEMENTS(1, "Vitamins/Supplements"),
    PRESCRIBED_MEDICINE(2, "Medications Rx"),
    FIRST_AID(3, "First-Aid Supplies"),
    UNLABELED(4, "Other"),
    OTHER(4, "Other");

    private int id;
    private String description;

    ProductType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
