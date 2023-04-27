import misc.Address;
import person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Address address1;
    Address address2;
    Person person1;

    @BeforeEach
    void setUp(){
        address1 = new Address("123 Main St", "Anytown", "CA", 12345);
        address2 = new Address("456 Elm St", "Anytown", "NY", 67890);

        person1 = new Person("John Doe", "555-555-1234", address1);

    }

    @Test
    void testToString() {
        String expected = "Person.Person{name='John Doe', phoneNumber='555-555-1234', address=Misc.Address{street=123 Main St, city=Anytown, state=CA, postalCode=12345}}";
        assertEquals(expected, person1.toString());
    }

    @Test
    void testEquals() {
        Person person1Copy =new Person("John Doe", "555-555-1234", address1);
        assertEquals(person1, person1Copy);

        Person person2 =new Person("John Doe", "555-555-1234", address2);
        assertNotEquals(person1, person2);
    }

    @Test
    void testHashCode() {
        Person person1Copy =new Person("John Doe", "555-555-1234", address1);

        // Check that the hash codes are equal
        assertEquals(person1.hashCode(), person1Copy.hashCode());
    }
}