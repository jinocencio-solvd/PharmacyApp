import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharmacistTest {
    Pharmacist pharmacist1;
    Pharmacist pharmacist2;
    Pharmacist pharmacist1Copy;
    Address address1;
    Address address2;

    @BeforeEach
    void setUp() {
        address1 = new Address("123 Main St", "Anytown", "CA", 12345);
        pharmacist1 = new Pharmacist("John Doe", "555-1234",
                address1, "E001", "CA-12345");

        address2 = new Address("456 Elm St", "Anytown", "NY", 67890);
        pharmacist2 = new Pharmacist("Jane Smith", "555-5678",
                address2, "E002", "NY-67890");

        pharmacist1Copy = new Pharmacist("John Doe", "555-1234",
                address1, "E001", "CA-12345");
    }


    @Test
    void testToString() {
        String expected = "Pharmacist{stateLicenseId='CA-12345'} " +
                "Employee{employeeId='E001'} Person{name='John Doe', " +
                "phoneNumber='555-1234', address=Address{street=123 Main St, " +
                "city=Anytown, state=CA, postalCode=12345}}";
        assertEquals(expected, pharmacist1.toString());
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