package genericLinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Patient;

public class CustomerLine extends GenericQueue<AbstractCustomer> {

    private static final Logger LOG = LogManager.getLogger(CustomerLine.class);

    public void addCustomer(AbstractCustomer abstractCustomer) {
        LOG.trace(abstractCustomer.getName() + " is now in line");
        enqueue(abstractCustomer);
    }

    public boolean hasNext() {
        return !isEmpty();
    }

    public AbstractCustomer getNextCustomer() {
        if (isEmpty()) {
            LOG.warn("The line is empty");
            return null;
        }
        LOG.trace("The next customer is: " + peek().getName());
        return dequeue();
    }

    public int getLineLength() {
        LOG.debug("There are " + size() + " people in line");
        return size();
    }

    public boolean isCustomerPatient(AbstractCustomer abstractCustomer) {
        return abstractCustomer instanceof Patient;
    }
}
