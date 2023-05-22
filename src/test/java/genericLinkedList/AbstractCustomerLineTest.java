package genericLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import setup.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Customer;
import person.Patient;

class AbstractCustomerLineTest {
    private CustomerLine customerLine;
    private final Patient[] PATIENTS = DataProvider.predefinedPatients();
    private final Customer[] Customers = DataProvider.predefinedCustomers();
    @BeforeEach
    void setUp() {
        customerLine = new CustomerLine();
    }

    @Test
    void addCustomer() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(Customers[0]);
        customerLine.addCustomer(Customers[1]);
        assertEquals(3, customerLine.getLineLength());
    }

    @Test
    void getNextCustomer() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(Customers[0]);
        customerLine.addCustomer(Customers[1]);
        assertEquals(PATIENTS[0], customerLine.getNextCustomer());
        assertEquals(Customers[0], customerLine.getNextCustomer());
        assertEquals(1, customerLine.getLineLength());
    }

    @Test
    void getLineLength() {
        customerLine.addCustomer(PATIENTS[0]);
        customerLine.addCustomer(Customers[0]);
        customerLine.addCustomer(Customers[1]);
        customerLine.getNextCustomer();
        assertEquals(2, customerLine.getLineLength());
    }

    @Test
    void isCustomerPatient() {
        assertTrue(customerLine.isCustomerPatient(PATIENTS[0]));
        assertFalse(customerLine.isCustomerPatient(Customers[0]));

    }
}