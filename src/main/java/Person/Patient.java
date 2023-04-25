package Person;

import Misc.Address;
import Misc.Insurance;

import Pharmacy.Pharmacy;
import Product.Prescription;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Person.Patient class represents a person who is a patient, with a patient ID and insurance
 * information.
 */
public class Patient extends Customer {
    private static final Logger LOG = LogManager.getLogger(Patient.class);
    private static int count = 0;

    private String patientID;
    private String insuranceName;
    private double percentInsuranceCovered;
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

    public static Patient[] predefinedPatients() {
        Address[] addresses = Address.predefinedAddresses();
        Insurance[] insurances = Insurance.predefinedInsurance();
        return new Patient[]{
            new Patient("Tom Davis", "555-7890", addresses[5], insurances[0]),
            new Patient("Sara Johnson", "555-2345", addresses[6], insurances[1]),
            new Patient("Sara Johnson", "555-2345", addresses[6])
        };
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

    public double getPercentInsuranceCovered() {
        return percentInsuranceCovered;
    }

    public void setPercentInsuranceCovered(double percentInsuranceCovered) {
        this.percentInsuranceCovered = percentInsuranceCovered;
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

    @Override
    public void providePrescription(Pharmacy pharmacy, Prescription prescription) {
        pharmacy.getPrescriptionRegistry().addPrescription(this, prescription);
    }

    @Override
    public String toString() {
        return "Person.Patient{" +
            "patientID='" + patientID + '\'' +
            ", insuranceName='" + insuranceName + '\'' +
            ", percentInsuranceCovered=" + percentInsuranceCovered +
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
        return
            Double.compare(patient.getPercentInsuranceCovered(), getPercentInsuranceCovered())
                == 0 && Objects.equals(getPatientID(), patient.getPatientID())
                && Objects.equals(getInsuranceName(), patient.getInsuranceName())
                && Objects.equals(getInsurance(), patient.getInsurance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPatientID(), getInsuranceName(),
            getPercentInsuranceCovered(), getInsurance());
    }
}