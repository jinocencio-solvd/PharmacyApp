package Product;

import Person.Patient;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Product.Prescription class represents a prescription with a prescription ID, number of
 * refills, filled status, medication, and patient.
 */
public class Prescription {

    private static final Logger LOG = LogManager.getLogger(Prescription.class);

    private String prescriptionId;
    private int numRefills;
    private boolean isFilled;
    private Medication medication;
    private Patient patient;
    private int prescribedQuantity;
    private static int countId = 0;

    /**
     * Constructs a new Prescription object with the given prescription ID, number of refills,
     * filled status, medication, patient, and prescribed quantity.
     *
     * @param numRefills         the number of refills allowed for the prescription
     * @param isFilled           the filled status of the prescription (true if filled, false
     *                           otherwise)
     * @param medication         the medication associated with the prescription
     * @param patient            the patient for whom the prescription was written
     * @param prescribedQuantity the quantity of medication prescribed
     */
    public Prescription(int numRefills, boolean isFilled,
        Medication medication, Patient patient, int prescribedQuantity) {
        countId++;
        this.prescriptionId = "rxID-" + countId;
        this.numRefills = numRefills;
        this.isFilled = isFilled;
        this.medication = medication;
        this.patient = patient;
        this.prescribedQuantity = prescribedQuantity;

        LOG.trace("Prescription created with Id: " + this.prescriptionId);
    }

    public static Prescription[] predefinedPrescriptions() {
        Patient[] patients = Patient.predefinedPatients();
        Medication[] medications = Medication.predefinedMedications();
        return new Prescription[]{
            new Prescription(2, false, medications[0], patients[0], 35),
            new Prescription(2, false, medications[1], patients[0], 50),
            new Prescription(2, false, medications[2], patients[1], 35)
        };
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
        return "Product.Prescription{" +
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
