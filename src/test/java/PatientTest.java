import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    Address address5;
    Patient patient1;
    Patient patient1Copy;
    Address address6;
    Patient patient2;

    @BeforeEach
    void setUp() {
        address5 = new Address("111 Maple St", "Another Town", "GA",
                97531);
        patient1 = new Patient("Tom Davis", "555-7890", address5, 1005,
                "Humana");
        patient1Copy = new Patient("Tom Davis", "555-7890", address5, 1005,
                "Humana");

        address6 = new Address("222 Cedar St", "Someplace", "VA",
                86420);
        patient2 = new Patient("Sara Johnson", "555-2345", address6, 1006,
                "Anthem");
    }

    @Test
    void testToString() {
        String expected = "Patient{patientID=1005, insuranceName='Humana'} " +
                "Person{name='Tom Davis', phoneNumber='555-7890', " +
                "address=Address{street=111 Maple St, city=Another Town, " +
                "state=GA, postalCode=97531}}";
        assertEquals(expected, patient1.toString());
    }

    @Test
    void testEquals() {
        assertEquals(patient1, patient1Copy);
        assertNotEquals(patient1, patient2);
    }

    @Test
    void testHashCode() {
        assertEquals(patient1.hashCode(), patient1Copy.hashCode());
        assertNotEquals(patient1.hashCode(), patient2.hashCode());
    }
}