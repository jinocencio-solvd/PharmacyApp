package prescriptionRegistry;

import enums.PrescriptionStatus;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Patient;
import product.Medication;


/**
 * The Product.Prescription class represents a prescription with a prescription ID, number of
 * refills, filled status, medication, and patient.
 */
public class Prescription {

    private static final Logger LOG = LogManager.getLogger(Prescription.class);
    private String prescriptionId;
    private int numRefills;
    private Medication medication;
    private Patient patient;
    private int prescribedQuantity;
    private static int countId = 0;
    private PrescriptionStatus prescriptionStatus;

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
        this.medication = medication;
        this.patient = patient;
        this.prescribedQuantity = prescribedQuantity;
        this.prescriptionStatus = PrescriptionStatus.NEW;

        LOG.trace("Prescription created with Id: " + this.prescriptionId);
    }

    public Prescription(Medication medication, int prescribedQuantity, int numRefills) {
        countId++;
        this.prescriptionId = "rxID-" + countId;
        this.numRefills = numRefills;
        this.medication = medication;
        this.prescribedQuantity = prescribedQuantity;
        this.prescriptionStatus = PrescriptionStatus.NEW;

        LOG.trace("Prescription created with Id: " + this.prescriptionId);
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

    public PrescriptionStatus getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    @Override
    public String toString() {
        return "Product.Prescription{" +
            "prescriptionId='" + prescriptionId + '\'' +
            ", numRefills=" + numRefills +
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
        return getNumRefills() == that.getNumRefills()
            && getPrescribedQuantity() == that.getPrescribedQuantity() && Objects.equals(
            getPrescriptionId(), that.getPrescriptionId()) && Objects.equals(getMedication(),
            that.getMedication()) && Objects.equals(getPatient(), that.getPatient())
            && getPrescriptionStatus() == that.getPrescriptionStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrescriptionId(), getNumRefills(), getMedication(), getPatient(),
            getPrescribedQuantity(), getPrescriptionStatus());
    }
}
