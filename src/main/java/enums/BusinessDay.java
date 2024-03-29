package enums;

public enum BusinessDay {
    MONDAY(1, "Monday - Open", true),
    TUESDAY(2, "Tuesday - Open", true),
    WEDNESDAY(3, "Wednesday - Open", true),
    THURSDAY(4, "Thursday - Open", true),
    FRIDAY(5, "Friday - Open", true),
    SATURDAY(6, "Saturday - Open", true),
    SUNDAY(7, "Sunday - Closed", false);

    private int id;
    private String description;
    private boolean isOpen;

    BusinessDay(int id, String description, boolean isOpen) {
        this.id = id;
        this.description = description;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public static BusinessDay getBusinessDay(String day) {
        return BusinessDay.valueOf(day);
    }

    public String getDescription() {
        return description;
    }
}