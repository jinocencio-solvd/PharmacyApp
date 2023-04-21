import java.util.Objects;

/**
 * The Prescription class represents a prescription with a prescription ID, number of refills,
 * filled status, medication, and patient.
 */
public class Prescription {

  private String prescriptionId;
  private int numRefills;
  private boolean isFilled;
  private Medication medication;
  private Patient patient;
  private int prescribedQuantity;

  public Prescription(String prescriptionId, int numRefills, boolean isFilled,
      Medication medication, Patient patient) {
    this.prescriptionId = prescriptionId;
    this.numRefills = numRefills;
    this.isFilled = isFilled;
    this.medication = medication;
    this.patient = patient;
    this.prescribedQuantity = 1;
  }

  public Prescription(String prescriptionId, int numRefills, boolean isFilled,
      Medication medication, Patient patient, int prescribedQuantity) {
    this.prescriptionId = prescriptionId;
    this.numRefills = numRefills;
    this.isFilled = isFilled;
    this.medication = medication;
    this.patient = patient;
    this.prescribedQuantity = prescribedQuantity;
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

  public int getPrescribedQuantity() {
    return prescribedQuantity;
  }

  public void setPrescribedQuantity(int prescribedQuantity) {
    this.prescribedQuantity = prescribedQuantity;
  }

  @Override
  public String toString() {
    return "Prescription{" +
        "prescriptionId='" + prescriptionId + '\'' +
        ", numRefills=" + numRefills +
        ", isFilled=" + isFilled +
        ", medication=" + medication +
        ", patient=" + patient +
        ", prescribedQuantity=" + prescribedQuantity +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Prescription)) {
      return false;
    }
    Prescription that = (Prescription) o;
    return getNumRefills() == that.getNumRefills() && isFilled() == that.isFilled()
        && getPrescriptionId().equals(that.getPrescriptionId()) && getMedication().equals(
        that.getMedication()) && getPatient().equals(that.getPatient());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPrescriptionId(), getNumRefills(), isFilled(), getMedication(),
        getPatient());
  }
}
