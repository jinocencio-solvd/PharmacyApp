package person;

import exceptions.InvalidPrescriptionException;
import java.util.Objects;
import misc.Address;
import misc.Insurance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;

/**
 * The Person.Patient class represents a person who is a patient, with a patient ID and insurance
 * information.
 */
public class Patient extends Customer {

    private static final Logger LOG = LogManager.getLogger(Patient.class);
    private static int count = 0;

    private String patientID;
    private String insuranceName;
    private Insurance insurance;

    // Represents a patient with insurance
    public Patient(String name, String phoneNumber, Address address, Insurance insurance) {
        super(name, phoneNumber, address);
        count++;
        this.patientID = "patientId-" + count;
        this.insurance = insurance;

        LOG.trace("Patient created with Id: " + patientID);
    }

    // Represents a patient without insurance
    public Patient(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
        count++;
        this.patientID = "patientId-" + count;
        this.insuranceName = null;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public static int getNumberOfPatients() {
        return count;
    }

    public void requestPrescriptionRefill(Pharmacy pharmacy, Prescription prescription) {
        try {
            LOG.info(
                "Patient " + this.getName() + " requests Rx refill for: " + prescription.getMedication()
                    .getName());
            pharmacy.receivePrescriptionRefillRequest(this, prescription);
        } catch (InvalidPrescriptionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void providePrescription(Pharmacy pharmacy, Prescription prescription) {
        try {
            pharmacy.receivePrescription(this, prescription);
            LOG.info(
                "Patient " + this.getName() + " provided Rx for: " + prescription.getMedication()
                    .getName());
        } catch (InvalidPrescriptionException e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public String toString() {
        return "Person.Patient{" +
            "patientID='" + patientID + '\'' +
            ", insuranceName='" + insuranceName + '\'' +
            ", insurance=" + insurance +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(getPatientID(), patient.getPatientID())
            && Objects.equals(getInsuranceName(), patient.getInsuranceName())
            && Objects.equals(getInsurance(), patient.getInsurance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPatientID(), getInsuranceName(), getInsurance());
    }
}