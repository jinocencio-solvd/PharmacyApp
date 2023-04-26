package PrescriptionRegistry;

import Exceptions.PersonDoesNotExistException;
import Person.Patient;
import Product.Prescription;
import java.util.ArrayList;

public interface IPrescriptionRegistry {

    ArrayList<Prescription> getPrescriptionList(Patient patient);

    void addPatientToRegistry(Patient patient);

    void addPrescription(Patient patient, Prescription prescription)
        throws PersonDoesNotExistException;
}
