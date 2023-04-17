import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    Address address1;
    Address address2;
    Address address3;
    Address address4;
    Address address5;
    Address address6;
    Address address6Copy;

    @BeforeEach
    void setUp() {
        address1 = new Address("123 Main St", "Anytown", "CA", 12345);
        address2 = new Address("456 Elm St", "Anytown", "NY", 67890);
        address3 = new Address("789 Oak St", "Sometown", "FL", 24680);
        address4 = new Address("321 Pine St", "Othertown", "TX", 13579);
        address5 = new Address("111 Maple St", "Another Town", "GA", 97531);
        address6 = new Address("222 Cedar St", "Someplace", "VA", 86420);
        address6Copy = new Address("222 Cedar St", "Someplace", "VA", 86420);
    }

    @Test
    void testToString() {
        String expected = "Address{street=123 Main St, city=Anytown, " +
                "state=CA, postalCode=12345}";

        assertEquals(expected, address1.toString());
    }

    @Test
    void testEquals() {
        assertEquals(address6, address6Copy);
        assertNotEquals(address6, address1);
    }

    @Test
    void testHashCode() {
        assertEquals(address6.hashCode(), address6Copy.hashCode());
        assertNotEquals(address6.hashCode(), address1.hashCode());
    }
}