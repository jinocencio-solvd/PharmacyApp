package prescriptionRegistry;

import enums.PrescriptionStatus;
import exceptions.InvalidPrescriptionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import person.Patient;
import product.Medication;

public class FilledPrescriptions {

    Map<Prescription, List<Medication>> filledPrescriptions = new HashMap<>();

    public void addFilledPrescription(Prescription prescription, List<Medication> medication) {
        filledPrescriptions.put(prescription, medication);
    }

    public List<Prescription> getFilledPrescriptionsByPatient(Patient patient)
        throws InvalidPrescriptionException {
        List<Prescription> filledPatientPrescriptions = filledPrescriptions.keySet().stream()
            .filter(prescription -> prescription.getPatient() == patient &&
                prescription.getPrescriptionStatus() == PrescriptionStatus.FILLED)
            .collect(Collectors.toList());
        if (filledPatientPrescriptions.isEmpty()) {
            throw new InvalidPrescriptionException("No filled prescriptions found for patient.");
        }
        return filledPatientPrescriptions;
    }

    public List<Medication> getMedicationsByPrescription(Prescription prescription) {
        return filledPrescriptions.get(prescription);
    }
}
