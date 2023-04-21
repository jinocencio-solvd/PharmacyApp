import java.util.ArrayList;
import java.util.Objects;

public class Pharmacy {

    private String name;
    private Address address;
    private String phoneNumber;
    private String emailAddress;
    private Inventory inventory;
    private ArrayList<Employee> employees;


    public Pharmacy(String name, Address address, String phoneNumber, String emailAddress,
        Inventory inventory, ArrayList<Employee> employees) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.inventory = inventory;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
            "name='" + name + '\'' +
            ", address=" + address +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", inventory=" + inventory +
            ", employees=" + employees +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pharmacy)) {
            return false;
        }
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(getName(), pharmacy.getName()) && Objects.equals(
            getAddress(), pharmacy.getAddress()) && Objects.equals(getPhoneNumber(),
            pharmacy.getPhoneNumber()) && Objects.equals(getEmailAddress(),
            pharmacy.getEmailAddress()) && Objects.equals(getInventory(),
            pharmacy.getInventory()) && Objects.equals(getEmployees(), pharmacy.getEmployees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getPhoneNumber(), getEmailAddress(),
            getInventory(),
            getEmployees());
    }
}
