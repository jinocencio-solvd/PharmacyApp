package genericLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import misc.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Consumer;
import person.Patient;

class CustomerLineTest {
    private CustomerLine customerLine;
    private final Patient[] PATIENTS = DataProvider.predefinedPatients();
    private final Consumer[] CONSUMERS = DataProvider.predefinedConsumers();
    @BeforeEach
    void setUp() {
        customerLine = new CustomerLine();
    }

    @Test
    void addCustomer() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(CONSUMERS[0]);
        customerLine.addCustomer(CONSUMERS[1]);
        assertEquals(3, customerLine.getLineLength());
    }

    @Test
    void getNextCustomer() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(CONSUMERS[0]);
        customerLine.addCustomer(CONSUMERS[1]);
        assertEquals(PATIENTS[0], customerLine.getNextCustomer());
        assertEquals(CONSUMERS[0], customerLine.getNextCustomer());
        assertEquals(1, customerLine.getLineLength());
    }

    @Test
    void getLineLength() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(CONSUMERS[0]);
        customerLine.addCustomer(CONSUMERS[1]);
        customerLine.getNextCustomer();
        assertEquals(2, customerLine.getLineLength());
    }

    @Test
    void isCustomerPatient() {
        assertTrue(customerLine.isCustomerPatient(PATIENTS[0]));
        assertFalse(customerLine.isCustomerPatient(CONSUMERS[0]));

    }
}