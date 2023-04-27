package prescriptionRegistry;

import exceptions.DuplicatePersonException;
import exceptions.PersonDoesNotExistException;
import person.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class PrescriptionRegistry implements IPrescriptionRegistry {

    private static final Logger LOG = LogManager.getLogger(PrescriptionRegistry.class);

    private final HashMap<Patient, ArrayList<Prescription>> prescriptionRegistry;

    public PrescriptionRegistry() {
        prescriptionRegistry = new HashMap<Patient, ArrayList<Prescription>>();
    }

    public ArrayList<Prescription> getPrescriptionList(Patient patient) {
        return prescriptionRegistry.get(patient);
    }

    public void addPatientToRegistry(Patient patient) {
        try {
            if (prescriptionRegistry.containsKey(patient)) {
                throw new DuplicatePersonException("Patient already exists in system");
            }
            prescriptionRegistry.put(patient, new ArrayList<>());
            LOG.info("Added patient " + patient.getPatientID() + " to prescriptionRegistry");
        } catch (DuplicatePersonException e) {
            LOG.info("Patient already exists in system");
        }
    }

    public void addPrescription(Patient patient, Prescription prescription)
        throws PersonDoesNotExistException {
        LOG.trace("Adding prescription " + prescription.getPrescriptionId() + " for patient "
            + patient.getPatientID());

        if (!prescriptionRegistry.containsKey(patient)) {
            LOG.warn("PersonDoesNotExistException was thrown");
            throw new PersonDoesNotExistException("The person is not registered in database");
        }

        prescriptionRegistry.get(patient).add(prescription);
    }

}
