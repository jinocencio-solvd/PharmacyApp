package Pharmacy;

import Exceptions.DuplicatePersonException;
import Exceptions.PersonDoesNotExistException;
import Inventory.Inventory;
import Misc.Address;
import Person.Employee;
import Person.Pharmacist;
import PrescriptionRegistry.PrescriptionRegistry;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pharmacy implements IPharmacy {

    private static final Logger LOG = LogManager.getLogger(Pharmacy.class);

    private String name;
    private Address address;
    private String phoneNumber;
    private String emailAddress;
    private Inventory inventory;
    private ArrayList<Employee> employees;
    private PrescriptionRegistry prescriptionRegistry;


    public Pharmacy(String name, Address address, String phoneNumber, String emailAddress,
        Inventory inventory, ArrayList<Employee> employees) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.inventory = inventory;
        this.employees = employees;
    }

    public Pharmacy(String name, Address address, String phoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.employees = new ArrayList<Employee>();
    }

    public PrescriptionRegistry getPrescriptionRegistry() {
        return prescriptionRegistry;
    }

    public void setPrescriptionRegistry(PrescriptionRegistry prescriptionRegistry) {
        this.prescriptionRegistry = prescriptionRegistry;
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


    /**
     * Hires a new employee and adds them to the pharmacy's list of employees.
     *
     * @param newEmployee the person to hire as an employee
     */
    @Override
    public void hireEmployee(Employee newEmployee) throws DuplicatePersonException {
        //TODO: Perhaps this would make more sense if hireEmployee took a "Person" as a parameter
        // and then create an "Employee". Logic flow would have to completely change.
        try {
            if (this.employees.contains(newEmployee)) {
                throw new DuplicatePersonException(
                    "The employee is already hired in the employee system.");
            }
            this.employees.add(newEmployee);
            LOG.info("Employee" + newEmployee.getEmployeeID()
                + " was successfully registered in employee database");
        } catch (DuplicatePersonException e) {
            LOG.warn("Employee is already registered in employee database");
        }
    }

    /**
     * Releases an employee and removes them from the pharmacy's list of employees.
     *
     * @param employee the employee to release from their position
     */
    @Override
    public void releaseEmployee(Employee employee) {
        LOG.trace("In releaseEmployee for " + employee.getEmployeeID());
        try {
            if (this.employees.contains((employee))) {
                this.employees.remove(employee);
                LOG.info("Employee " + employee.getEmployeeID() + " was released");
                return;
            }
            throw new PersonDoesNotExistException("Employee not found in employee database");
        } catch (PersonDoesNotExistException e) {
            LOG.error("Error occurred. Employee not found in employee database.");
        }
    }

    /**
     * Prints information about the pharmacy, including its name, address, phone number, and a list
     * of current employees.
     */
    @Override
    public void printPharmacyInformation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Pharmacy.Pharmacy{" +
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
