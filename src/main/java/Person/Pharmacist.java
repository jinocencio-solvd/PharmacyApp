package Person;

import Exceptions.InsufficientQuantityException;
import Exceptions.NoMoreRefillsException;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductOutOfStockException;
import Misc.Address;
import Product.*;
import Inventory.Inventory;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Person.Pharmacist class represents an employee who is a pharmacist, with a state license ID.
 */
public class Pharmacist extends Employee {

    private static final Logger LOG = LogManager.getLogger(Pharmacist.class);

    private String stateLicenseId;


    public Pharmacist(String name, String phoneNumber, Address address, String stateLicenseId) {
        super(name, phoneNumber, address);
        this.stateLicenseId = stateLicenseId;
        LOG.trace("Pharmacist created with eID: " + super.employeeId);
    }

    public static Pharmacist[] predefinedPharmacist() {
        Address[] addresses = Address.predefinedAddresses();
        return new Pharmacist[]{
            new Pharmacist("John Doe", "555-1234", addresses[1], "CA-12345"),
            new Pharmacist("Jane Smith", "555-5678", addresses[2], "NY-67890")};
    }

    public String getStateLicenseId() {
        return stateLicenseId;
    }

    public void setStateLicenseId(String stateLicenseId) {
        this.stateLicenseId = stateLicenseId;
    }

    public void retrieveMedicationsFromInventory(Inventory inventory, Prescription prescription) {
        Medication prescribedMed = prescription.getMedication();
        int prescribedQuantity = prescription.getPrescribedQuantity();
        try {
            inventory.removeProduct(prescribedMed, prescribedQuantity);
        } catch (InsufficientQuantityException e) {
            int medQuantity = inventory.getQuantity(prescribedMed);
            LOG.warn("Unable to retrieve prescribed quantity of +" + prescribedQuantity
                + ". Current quantity of " + prescribedMed.getName() + " is " + medQuantity + ".");
        } catch (ProductDoesNotExistException e) {
            LOG.warn("Medication for " + prescribedMed.getName() + "does not exist.");
        } catch (ProductOutOfStockException e) {
            LOG.warn("Medication " + prescribedMed.getName() + "is out of stock.");
        }
    }

    /**
     * Fills a prescription by removing the prescribed medication and quantity from the given
     * inventory, updating the prescription information, and printing a confirmation message to the
     * console.
     *
     * @param inventory    the inventory from which to remove the prescribed medication and
     *                     quantity
     * @param prescription the prescription to be filled
     */
    public void fillPrescription(Inventory inventory, Prescription prescription) {
        try {
            if (prescription.getNumRefills() == 0) {
                throw new NoMoreRefillsException("No more refills available");
            }
            retrieveMedicationsFromInventory(inventory, prescription);
            prescription.setNumRefills(prescription.getNumRefills() - 1);
            prescription.setFilled(true);

            // TODO: Upon patient picking up prescription,
            //  setFilled to false to be ready for next refill

            LOG.info("Prescription: " + prescription.getPrescriptionId() + " for patient: "
                + prescription.getPatient().getName() + " is filled.");
        } catch (NoMoreRefillsException e) {
            LOG.warn("Prescription is out of refills");
        }
    }

    /**
     * method to print out employee details
     */
    @Override
    public void printEmployeeDetails() {
        super.printDetails();
        System.out.println("Position Title: Person.Pharmacist");
        System.out.println("EmployeeId: " + super.employeeId);
        System.out.println("LicenseId: " + this.stateLicenseId);
    }

    @Override
    public String toString() {
        return "Person.Pharmacist{" +
            "stateLicenseId='" + stateLicenseId + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pharmacist)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Pharmacist that = (Pharmacist) o;
        return (getStateLicenseId().equals(that.getStateLicenseId())
            && getName().equals(that.getName())
            && getEmployeeID().equals(that.getEmployeeID()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStateLicenseId());
    }
}
