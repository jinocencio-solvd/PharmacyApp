public class Employee extends Person{
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
                '}';
    }
}
