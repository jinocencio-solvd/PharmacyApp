package Person;

import Misc.Address;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an employee that extends the Person.Person class. Contains an employee ID field in addition
 * to the Person.Person class fields.
 */
public abstract class Employee extends Person implements IEmployee {

    private static int count;
    protected String employeeId;

    static {
        count = 0;
    }

    public Employee(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
        count++;
        this.employeeId = "EID-" + count;
    }

    public String getEmployeeID() {
        return employeeId;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeId = employeeID;
    }

    private static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    @Override
    public void clockIn() {
        System.out.println("Clocking in at " + getCurrentDateTime());
    }

    @Override
    public void clockOut() {
        System.out.println("Clocking out at " + getCurrentDateTime());
    }

    @Override
    public String toString() {
        return "Person.Employee{" +
            "employeeId='" + employeeId + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
}
