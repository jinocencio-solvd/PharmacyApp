package enums;

public enum EmployeeType {
    FULL_TIME(0, "Full-time employee"),
    PART_TIME(1, "Part-time employee"),
    CONTRACTOR(2, "Contracted employee"),
    INTERN(3, "Intern");

    private int id;
    private String description;

    EmployeeType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
