package prescriptionRegistry;

import enums.PrescriptionStatus;
import exceptions.DuplicatePersonException;
import exceptions.PersonDoesNotExistException;
import person.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import setup.AppConfig;


public class PrescriptionRegistry implements IPrescriptionRegistry {

    private static final Logger LOG = LogManager.getLogger(PrescriptionRegistry.class);

    private final HashMap<Patient, ArrayList<Prescription>> prescriptionRegistry;

    public PrescriptionRegistry() {
        prescriptionRegistry = new HashMap<Patient, ArrayList<Prescription>>();
    }

    public ArrayList<Prescription> getPrescriptionList(Patient patient) {
        return prescriptionRegistry.getOrDefault(patient, new ArrayList<>());
    }

    public void addPatientToRegistry(Patient patient) {
        try {
            if (prescriptionRegistry.containsKey(patient)) {
                throw new DuplicatePersonException("Patient already exists in system");
            }
            prescriptionRegistry.put(patient, new ArrayList<>());
            LOG.trace("Added patient " + patient.getPatientID() + " to prescriptionRegistry");
        } catch (DuplicatePersonException e) {
            LOG.trace("Patient already exists in system");
        }
    }

    public void addPrescription(Patient patient, Prescription prescription)
        throws PersonDoesNotExistException {
        if (AppConfig.SHOW_PRESCRIPTION_REGISTRY_LOGS) {
            LOG.trace("Adding prescription " + prescription.getPrescriptionId() + " for patient "
                + patient.getPatientID());
        }

        if (!prescriptionRegistry.containsKey(patient)) {
            if (AppConfig.SHOW_PRESCRIPTION_REGISTRY_LOGS)LOG.warn("PersonDoesNotExistException was thrown");
            throw new PersonDoesNotExistException("The person is not registered in database");
        }
        prescription.setPrescriptionStatus(PrescriptionStatus.PENDING);
        prescriptionRegistry.get(patient).add(prescription);
    }

    public void updatePrescriptionRegistry(Prescription prescription) {
        Patient patient = prescription.getPatient();
        ArrayList<Prescription> prescriptionList = prescriptionRegistry.get(patient);
        for (Prescription p : prescriptionList) {
            if (p.getPrescriptionId().equals(prescription.getPrescriptionId())) {
                prescriptionList.remove(p);
                prescriptionList.add(prescription);
            }
        }
    }

}
