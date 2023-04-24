import java.util.Objects;

/**
 * The Patient class represents a person who is a patient, with a patient ID and insurance
 * information.
 */
public class Patient extends Customer {

    private int patientID;
    private String insuranceName;
    private double percentInsuranceCovered;
    private Insurance insurance;

    public Patient(String name, String phoneNumber, Address address, int patientID,
        Insurance insurance) {
        super(name, phoneNumber, address);
        this.patientID = patientID;
        this.insurance = insurance;
    }

    public Patient(String name, String phoneNumber, Address address, int patientID) {
        super(name, phoneNumber, address);
        this.patientID = patientID;
        this.insuranceName = null;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
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

    @Override
    public String toString() {
        return "Patient{" +
            "patientID=" + patientID +
            ", insuranceName='" + insuranceName + '\'' +
            ", percentInsuranceCovered=" + percentInsuranceCovered +
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
        Patient patient = (Patient) o;
        return getPatientID() == patient.getPatientID() &&
            Objects.equals(getInsuranceName(),
                patient.getInsuranceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientID(), getInsuranceName());
    }

    /**
     * Provides a prescription for the given prescription, which can be used to purchase
     * medication.
     *
     * @param pharmacy     the pharmacy to provide the prescription to
     * @param prescription the prescription to provide
     */
    @Override
    public void providePrescription(Pharmacy pharmacy, Prescription prescription) {
        pharmacy.getPrescriptionRegistry().addPrescription(this, prescription);
    }
}