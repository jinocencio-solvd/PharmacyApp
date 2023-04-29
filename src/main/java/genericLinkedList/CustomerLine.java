package genericLinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Customer;
import person.Patient;

public class CustomerLine extends GenericQueue<Customer> {

    private static final Logger LOG = LogManager.getLogger(CustomerLine.class);

    public GenericQueue<Customer> customerLine;

    public CustomerLine() {
        this.customerLine = new GenericQueue<>();
    }

    public void addCustomer(Customer customer) {
        customerLine.enqueue(customer);
    }

    public Customer getNextCustomer() {
        if (customerLine.isEmpty()) {
            LOG.info("The line is empty");
            return null;
        }
        return customerLine.dequeue();
    }

    public int getLineLength() {
        return customerLine.size();
    }

    public boolean isCustomerPatient(Customer customer) {
        return customer instanceof Patient;
    }
}
