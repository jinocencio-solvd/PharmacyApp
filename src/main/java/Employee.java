import java.util.Objects;

/**
 * Represents an employee that extends the Person class.
 * Contains an employee ID field in addition to the Person class fields.
 */
public class Employee extends Person {
    private String employeeId;

    public Employee(String name, String phoneNumber, Address address,
                    String employeeID) {
        super(name, phoneNumber, address);
        this.employeeId = employeeID;
    }

    public String getEmployeeID() {
        return employeeId;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeId = employeeID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
}
