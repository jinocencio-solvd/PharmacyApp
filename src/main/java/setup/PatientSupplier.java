package setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import person.Patient;

public class PatientSupplier implements Supplier<Patient> {
    private static final int PATIENT_MAX_BALANCE = 1000;

    private static List<Patient> patientList = new ArrayList<>(List.of(DataProvider.predefinedPatients()));


    @Override
    public Patient get() {
        int randomInt = new Random().nextInt(patientList.size());
        Patient randomPatient = patientList.get(randomInt);
        randomPatient.setCreditBalance(PATIENT_MAX_BALANCE);
        patientList.remove(randomInt);
        return randomPatient;
    }
}
