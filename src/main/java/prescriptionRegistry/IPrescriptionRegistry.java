package prescriptionRegistry;

import exceptions.PersonDoesNotExistException;
import person.Patient;
import java.util.ArrayList;

public interface IPrescriptionRegistry {

    ArrayList<Prescription> getPrescriptionList(Patient patient);

    void addPatientToRegistry(Patient patient);

    void addPrescription(Patient patient, Prescription prescription)
        throws PersonDoesNotExistException;
}
