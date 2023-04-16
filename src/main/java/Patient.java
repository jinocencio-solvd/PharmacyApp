public class Patient extends Person {
    private int patientID;
    private String insuranceName;

    public Patient(String name, String phoneNumber, Address address,
                   int patientID, String insuranceName) {
        super(name, phoneNumber, address);
        this.patientID = patientID;
        this.insuranceName = insuranceName;
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

    public String getPatientInfo() {
        return "Patient{" +
                "name=" + getName() +
                ", phoneNumber=" + getPhoneNumber() +
                ", address=" + getAddress() +
                ", patientID=" + patientID +
                ", insuranceName=" + insuranceName +
                '}';
    }
}