import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        // create a new patient for testing
        Address address = new Address("123 Main St", "Anytown", "CA", 12345);
        patient = new Patient("John Doe", "555-1234", address, 123, "Blue " +
                "Cross");
    }

    @Test
    void getPatientInfo() {
        String expected = "Patient{name=John Doe, phoneNumber=555-1234, " +
                "address=Address{street=123 Main St, city=Anytown, " +
                "state=CA, postalCode=12345}, patientID=123, " +
                "insuranceName=Blue Cross}";
        assertEquals(expected, patient.getPatientInfo());
    }
}