import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacyTechnicianTest {
    PharmacyTechnician tech1;
    PharmacyTechnician tech2;
    PharmacyTechnician tech1Copy;

    @BeforeEach
    void setUp() {
        Address address3 = new Address("789 Oak St", "Sometown", "FL", 24680);
        Address address4 = new Address("321 Pine St", "Othertown", "TX", 13579);
        tech1 = new PharmacyTechnician("John Tech", "555" +
                "-1234", address3, "EMP-001", "CA123456");
        tech1.setCashierTrained(true);


        tech2 = new PharmacyTechnician("Jane Tech", "555" +
                "-5678", address4, "EMP-002", "CA654321");
        tech1Copy = new PharmacyTechnician("John Tech",
                "555-1234", address3, "EMP-001", "CA123456");
    }

    @Test
    void testToString() {
        String expected = "PharmacyTechnician{stateLicenseId='CA123456', " +
                "isCashierTrained=true} Employee{employeeId='EMP-001'} " +
                "Person{name='John Tech', phoneNumber='555-1234', " +
                "address=Address{street=789 Oak St, city=Sometown, state=FL, " +
                "postalCode=24680}}";
        assertEquals(expected, tech1.toString());
    }

    @Test
    void testEquals() {
        assertNotEquals(tech1, tech1Copy);
        tech1Copy.setCashierTrained(true);
        assertEquals(tech1, tech1Copy);
    }

    @Test
    void testHashCode() {
        assertEquals(tech1.hashCode(), tech1.hashCode());
        assertNotEquals(tech1.hashCode(), tech2.hashCode());
        assertNotEquals(tech1.hashCode(), tech1Copy.hashCode());
        tech1Copy.setCashierTrained(true);
        assertEquals(tech1.hashCode(), tech1Copy.hashCode());
    }
}