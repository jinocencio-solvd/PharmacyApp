package person;

import exceptions.PersonDoesNotExistException;
import misc.Address;
import pharmacy.Pharmacy;
import prescriptionRegistry.Prescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends AbstractCustomer {

    private static final Logger LOG = LogManager.getLogger(Customer.class);

    public Customer(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }

    /**
     * Provides a prescription for the given prescription, which can be used to purchase
     * medication.
     *
     * @param pharmacy     the pharmacy to provide the prescription to
     * @param prescription the prescription to provide
     */
    @Override
    public void providePrescription(Pharmacy pharmacy, Prescription prescription) {
        Patient newPatient = new Patient(super.name, super.phoneNumber, super.address);
        if (newPatient.getInsurance() != null) {
            newPatient.setInsurance(newPatient.getInsurance());
        }
        pharmacy.getPrescriptionRegistry().addPatientToRegistry(newPatient);
        try {
            pharmacy.getPrescriptionRegistry().addPrescription(newPatient, prescription);
        } catch (PersonDoesNotExistException e) {
            LOG.error("An error occurred. Customer may already be a patient.");
        }
    }
}
