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
        LOG.info(customer.getName() + " is now in line");
        customerLine.enqueue(customer);
    }

    public Customer getNextCustomer() {
        if (customerLine.isEmpty()) {
            LOG.warn("The line is empty");
            return null;
        }
        LOG.info("The next customer is: " + customerLine.peek().getName());
        return customerLine.dequeue();
    }

    public int getLineLength() {
        LOG.debug("There are " + customerLine.size() + " people in line");
        return customerLine.size();
    }

    public boolean isCustomerPatient(Customer customer) {
        return customer instanceof Patient;
    }
}
