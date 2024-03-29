package person;

import static setup.AppConfig.SHOW_RX_STATUS_FLOW;

import enums.PrescriptionStatus;
import exceptions.InsufficientQuantityException;
import exceptions.NoMoreRefillsException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import inventory.Inventory;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import misc.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prescriptionRegistry.PrescriptionFilledLog;
import prescriptionRegistry.Prescription;
import prescriptionRegistry.PrescriptionRegistry;
import prescriptionRegistry.PrescriptionRequestLog;
import product.Medication;

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


    public String getStateLicenseId() {
        return stateLicenseId;
    }

    public void setStateLicenseId(String stateLicenseId) {
        this.stateLicenseId = stateLicenseId;
    }

    public List<Medication> getMedicationsFromInventory(Inventory inventory,
        Prescription prescription) {
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
        return Stream.generate(() -> prescribedMed)
            .limit(prescribedQuantity)
            .collect(Collectors.toList());
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
    public void fillPrescription(PrescriptionFilledLog prescriptionFilledLog, Inventory inventory,
        Prescription prescription, PrescriptionRegistry prescriptionRegistry) {
        try {
            switch (prescription.getPrescriptionStatus()) {
                case COMPLETED:
                    throw new NoMoreRefillsException("No more refills available");
                case CANCELLED:
                    throw new NoMoreRefillsException("The prescription has been cancelled");
                default:
                    List<Medication> medsFromRx = getMedicationsFromInventory(inventory,
                        prescription);
                    if (SHOW_RX_STATUS_FLOW) {
                        LOG.info(
                            "Pharmacist retrieved " + medsFromRx.get(0).getName()
                                + " from inventory to fill the prescription for " + prescription.getPatient().getName());
                    }
                    prescription.setPrescriptionStatus(PrescriptionStatus.FILLED);
                    if (SHOW_RX_STATUS_FLOW) {
                        LOG.warn(
                            "Pharmacist changed Rx for: " + prescription.getPatient().getName()
                                + " to "
                                + prescription.getPrescriptionStatus());
                    }
                    prescriptionFilledLog.addFilledPrescription(prescription, medsFromRx);


            }
        } catch (NoMoreRefillsException e) {
            LOG.error((e.getMessage()));
        }
        LOG.trace("Prescription: " + prescription.getPrescriptionId() + " for patient: "
            + prescription.getPatient().getName() + " is filled.");
    }

    public void fulfillAllPrescriptionLogRequests(PrescriptionFilledLog prescriptionFilledLog,
        PrescriptionRequestLog prescriptionRequestLog, Inventory inventory,
        PrescriptionRegistry prescriptionRegistry) {

        while (!prescriptionRequestLog.isEmpty()) {
            Prescription prescriptionToFulfill = prescriptionRequestLog.getNextPrescriptionRequest();
            fillPrescription(prescriptionFilledLog, inventory, prescriptionToFulfill,
                prescriptionRegistry);
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
