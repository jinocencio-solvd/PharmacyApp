package pharmacy;

import exceptions.DuplicatePersonException;
import exceptions.InvalidPrescriptionException;
import exceptions.PersonDoesNotExistException;
import prescriptionRegistry.FilledPrescriptions;
import prescriptionRegistry.PrescriptionRequestLog;
import inventory.Inventory;
import java.time.DayOfWeek;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.function.Predicate;
import misc.Address;
import enums.BusinessDay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Employee;
import person.Patient;
import prescriptionRegistry.Prescription;
import prescriptionRegistry.PrescriptionRegistry;
import enums.PrescriptionStatus;

/**
 * Represents a pharmacy with HR capabilities. This class represents a pharmacy, which has a name,
 * address, phone number, email address, and  a list of employees.
 */
public class Pharmacy implements IPharmacy {

    private static final Logger LOG = LogManager.getLogger(Pharmacy.class);

    private String name;
    private Address address;
    private String phoneNumber;
    private String emailAddress;
    private Inventory inventory;
    private LinkedHashSet<Employee> employees;
    private PrescriptionRegistry prescriptionRegistry;
    private PrescriptionRequestLog prescriptionRequestLog;
    private FilledPrescriptions filledPrescriptions;

    /**
     * Constructs a new Pharmacy object.
     *
     * @param name         The name of the pharmacy.
     * @param address      The address of the pharmacy.
     * @param phoneNumber  The phone number of the pharmacy.
     * @param emailAddress The email address of the pharmacy.
     */
    public Pharmacy(String name, Address address, String phoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.employees = new LinkedHashSet<>();
        this.prescriptionRegistry = new PrescriptionRegistry();
        this.prescriptionRequestLog = new PrescriptionRequestLog();
        this.filledPrescriptions = new FilledPrescriptions();
    }

    public FilledPrescriptions getFilledPrescriptions() {
        return filledPrescriptions;
    }

    public PrescriptionRegistry getPrescriptionRegistry() {
        return prescriptionRegistry;
    }

    public void setPrescriptionRegistry(PrescriptionRegistry prescriptionRegistry) {
        this.prescriptionRegistry = prescriptionRegistry;
    }

    public PrescriptionRequestLog getPrescriptionRequestLog() {
        return prescriptionRequestLog;
    }

    public void setPrescriptionRequestLog(PrescriptionRequestLog prescriptionRequestLog) {
        this.prescriptionRequestLog = prescriptionRequestLog;
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

    public LinkedHashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(LinkedHashSet<Employee> employees) {
        this.employees = employees;
    }

    public boolean isOpen(DayOfWeek dayToCheck) {
        Predicate<DayOfWeek> checkDay = day -> {
            BusinessDay businessDay = BusinessDay.getBusinessDay(dayToCheck.name());
            return day.getValue() == businessDay.getId() && businessDay.isOpen();
        };
        return checkDay.test(dayToCheck);
    }

    public void receivePrescription(Patient patient, Prescription prescription)
        throws InvalidPrescriptionException {
        if (!patient.equals(prescription.getPatient())) {
            throw new InvalidPrescriptionException(
                "The prescription does not belong to the patient.");
        }
        try {
            this.getPrescriptionRegistry().addPrescription(patient, prescription);
            prescriptionRequestLog.addPrescriptionRequest(prescription);

        } catch (PersonDoesNotExistException e) {
            LOG.info("New Patient added into registry");
            this.getPrescriptionRegistry().addPatientToRegistry(patient);
            receivePrescription(patient, prescription);

        }
    }

    public void receivePrescriptionRefillRequest(Patient patient, Prescription prescription)
        throws InvalidPrescriptionException {
        if (!patient.equals(prescription.getPatient())) {
            throw new InvalidPrescriptionException(
                "The prescription does not belong to the patient.");
        }
        if (isPatientPrescriptionRefillable(prescription)) {
            prescriptionRequestLog.addPrescriptionRequest(prescription);
            int numRefillsAfterFilled = prescription.getNumRefills() - 1;
            LOG.info("Refill request accepted. Refills remaining: " + numRefillsAfterFilled);
        } else {
            LOG.error("Refill request denied");
        }
    }

    /**
     * Checks whether the patient prescription request is valid
     *
     * @param prescription prescription concerning the patient
     */
    public boolean isPatientPrescriptionRefillable(Prescription prescription) {
        boolean isRefillable =
            prescription.getPrescriptionStatus() == PrescriptionStatus.REFILL_UPON_REQUEST;
        if (!isRefillable) {
            LOG.error("Unable to refill. There are no more refills available.");
        } else {
            LOG.info("Current request is refillable");
        }
        return isRefillable;
    }

    /**
     * Hires a new employee and adds them to the pharmacy's list of employees.
     *
     * @param newEmployee the person to hire as an employee
     */
    @Override
    public void hireEmployee(Employee newEmployee) {
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
