package genericLinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Patient;

public class CustomerLine extends GenericQueue<AbstractCustomer> {

    private static final Logger LOG = LogManager.getLogger(CustomerLine.class);

    public GenericQueue<AbstractCustomer> customerLine;

    public CustomerLine() {
        this.customerLine = new GenericQueue<>();
    }

    public void addCustomer(AbstractCustomer abstractCustomer) {
        LOG.info(abstractCustomer.getName() + " is now in line");
        customerLine.enqueue(abstractCustomer);
    }

    public AbstractCustomer getNextCustomer() {
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

    public boolean isCustomerPatient(AbstractCustomer abstractCustomer) {
        return abstractCustomer instanceof Patient;
    }
}
