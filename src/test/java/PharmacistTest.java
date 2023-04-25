import Person.Pharmacist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacistTest {

    Pharmacist pharmacist1;
    Pharmacist pharmacist2;
    Pharmacist pharmacist1Copy;
    Pharmacist[] pharmacists = Pharmacist.predefinedPharmacist();

    @BeforeEach
    void setUp() {
        pharmacist1 = pharmacists[0];
        pharmacist1Copy = new Pharmacist(pharmacist1.getName(), pharmacist1.getPhoneNumber(),
            pharmacist1.getAddress(), pharmacist1.getStateLicenseId());
        pharmacist1Copy.setEmployeeID(pharmacist1.getEmployeeID());
        pharmacist2 = pharmacists[1];
    }


    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
        assertEquals(pharmacist1, pharmacist1Copy);
        pharmacist1Copy.setName("James");
        assertNotEquals(pharmacist1, pharmacist1Copy);
    }

    @Test
    void testHashCode() {
        assertEquals(pharmacist1.hashCode(), pharmacist1Copy.hashCode());
    }
}