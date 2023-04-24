import java.util.ArrayList;
import java.util.HashMap;


public final class PrescriptionRegistry {

    private final HashMap<Patient, ArrayList<Prescription>> prescriptionRegistry;

    public PrescriptionRegistry() {
        prescriptionRegistry = new HashMap<Patient, ArrayList<Prescription>>();
    }

    public ArrayList<Prescription> getPrescriptionList(Patient patient) {
        return prescriptionRegistry.get(patient);
    }

    public void addPatientToRegistry(Patient patient) {
        // check if the patient exists in the registry
        if (!prescriptionRegistry.containsKey(patient)) {
            // if not, add patient to registry with empty list of prescriptions
            prescriptionRegistry.put(patient, new ArrayList<Prescription>());
        }
    }

    public void addPrescription(Patient patient, Prescription prescription) {
        //TODO: throw error if patient does not exist in registry
        prescriptionRegistry.get(patient).add(prescription);
    }
}
