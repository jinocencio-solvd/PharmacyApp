package misc;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Patient;

public class ConcurrentCustomerLine extends ConcurrentLinkedQueue<AbstractCustomer>{

    private static final Logger LOG = LogManager.getLogger(ConcurrentCustomerLine.class);

    public void addCustomer(AbstractCustomer abstractCustomer) {
        LOG.info(abstractCustomer.getName() + " is now in line");
        offer(abstractCustomer);
    }

    public boolean hasNext(){
        return !isEmpty();
    }

    public AbstractCustomer getNextCustomer() {
        AbstractCustomer customer = poll();
        if (customer == null) {
            LOG.warn("The line is empty");
        } else {
            LOG.info("The next customer is: " + customer.getName());
        }
        return customer;
    }

    public int getLineLength() {
        int length = size();
        LOG.debug("There are " + length + " people in line");
        return length;
    }

    public boolean isCustomerPatient(AbstractCustomer customer) {
        return customer instanceof Patient;
    }
}
