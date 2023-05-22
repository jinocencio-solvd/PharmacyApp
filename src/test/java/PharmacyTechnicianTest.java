import setup.DataProvider;
import person.PharmacyTechnician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTechnicianTest {

    PharmacyTechnician tech1;
    PharmacyTechnician tech2;
    PharmacyTechnician tech1Copy;
    PharmacyTechnician[] technicians = DataProvider.predefinedPharmacyTechnicians();

    @BeforeEach
    void setUp() {
        tech1 = technicians[0];
        tech1Copy = new PharmacyTechnician(tech1.getName(), tech1.getPhoneNumber(),
            tech1.getAddress(), tech1.getStateLicenseId());
        tech1Copy.setEmployeeID(tech1.getEmployeeID());
        tech2 = technicians[1];
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
        assertEquals(tech1, tech1Copy);
        tech1.setCashierTrained(true);
        assertNotEquals(tech1, tech1Copy);
    }

    @Test
    void testHashCode() {
        assertEquals(tech1.hashCode(), tech1.hashCode());
        assertNotEquals(tech1.hashCode(), tech2.hashCode());
        assertEquals(tech1.hashCode(), tech1Copy.hashCode());
        tech1Copy.setCashierTrained(true);
        assertNotEquals(tech1.hashCode(), tech1Copy.hashCode());
    }
}