public class Prescription {
    private String prescriptionId;
    private int numRefills;
    private boolean isFilled;
    Medication medication;
    Patient patient;

    public Prescription(String prescriptionId, int numRefills,
                        boolean isFilled, Medication medication,
                        Patient patient) {
        this.prescriptionId = prescriptionId;
        this.numRefills = numRefills;
        this.isFilled = isFilled;
        this.medication = medication;
        this.patient = patient;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getNumRefills() {
        return numRefills;
    }

    public void setNumRefills(int numRefills) {
        this.numRefills = numRefills;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", numRefills=" + numRefills +
                ", isFilled=" + isFilled +
                ", medication=" + medication +
                ", patient=" + patient +
                '}';
    }
}
