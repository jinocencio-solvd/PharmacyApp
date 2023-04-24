public class Consumer extends Customer {

    public Consumer(String name, String phoneNumber, Address address) {
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
        // create a new Patient object and copy over the relevant information
        //TODO: generate patientID via static class counter
        Patient newPatient = new Patient(super.name, super.phoneNumber, super.address, 111);
        if (newPatient.getInsurance() != null) {
            newPatient.setInsurance(newPatient.getInsurance());
        }
        pharmacy.getPrescriptionRegistry().addPatientToRegistry(newPatient);
        pharmacy.getPrescriptionRegistry().addPrescription(newPatient, prescription);
    }
}
