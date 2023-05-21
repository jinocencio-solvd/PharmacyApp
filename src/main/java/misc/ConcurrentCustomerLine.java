package misc;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.AbstractCustomer;
import person.Patient;
import setup.CustomerSupplier;
import setup.PatientSupplier;

public class ConcurrentCustomerLine extends ConcurrentLinkedQueue<AbstractCustomer> {

    private static final Logger LOG = LogManager.getLogger(ConcurrentCustomerLine.class);

    public void addCustomer(AbstractCustomer abstractCustomer) {
        LOG.info(abstractCustomer.getName() + " is now in line");
        offer(abstractCustomer);
    }

    public void addCustomer() {
        int randomNumber = new Random().nextInt(2);
        AbstractCustomer abstractCustomer =
            randomNumber == 0 ? new CustomerSupplier().get() : new PatientSupplier().get();
        LOG.info(abstractCustomer.getName() + " is now in line");
        offer(abstractCustomer);
    }

    public boolean hasNext() {
        return !isEmpty();
    }

    public AbstractCustomer getNextCustomer() {
        AbstractCustomer customer = poll();
        if (customer == null) {
            LOG.warn("The line is empty" + " from thread "
                + Thread.currentThread().getName());
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
