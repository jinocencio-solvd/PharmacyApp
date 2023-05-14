package prescriptionRegistry;

import enums.PrescriptionStatus;
import exceptions.InvalidPrescriptionException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import person.Patient;
import product.Medication;

public class FilledPrescriptions {

    List<Prescription> filledPrescriptions = new LinkedList<>();

    public void addFilledPrescription(Prescription prescription) {
        filledPrescriptions.add(prescription);
    }

    public Prescription getPrescriptionByPatientAndMedication(Patient patient,
        String medicationName) throws InvalidPrescriptionException {
        Optional<Prescription> prescription = filledPrescriptions.stream()
            .filter((p -> p.getPatient() == patient
                && Objects.equals(p.getMedication().getName(), medicationName)
                && p.getPrescriptionStatus() == PrescriptionStatus.FILLED))
            .findFirst();
        return prescription.orElseThrow(
            () -> new InvalidPrescriptionException("Prescription is not yet filled"));
    }

    public Medication getMedicationsByPrescription() {
        return null;
    }
}
