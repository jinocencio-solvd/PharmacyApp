import java.util.Objects;

/**
 * The Patient class represents a person who is a patient, with a patient ID and insurance
 * information.
 */
public class Patient extends Person {

    private int patientID;
    private String insuranceName;
    private double percentInsuranceCovered;

    public Patient(String name, String phoneNumber, Address address, int patientID) {
        super(name, phoneNumber, address);
        this.patientID = patientID;
        this.insuranceName = null;
        this.percentInsuranceCovered = 0;
    }

    public Patient(String name, String phoneNumber, Address address, int patientID,
        String insuranceName, double percentInsuranceCovered) {
        super(name, phoneNumber, address);
        this.patientID = patientID;
        this.insuranceName = insuranceName;
        this.percentInsuranceCovered = percentInsuranceCovered;
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
}