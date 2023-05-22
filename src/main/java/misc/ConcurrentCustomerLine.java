package misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        LOG.trace(abstractCustomer.getName() + " is now in line");
        offer(abstractCustomer);
    }

    public void addCustomer() {
        int randomNumber = new Random().nextInt(2);
        AbstractCustomer abstractCustomer =
            randomNumber == 0 ? new CustomerSupplier().get() : new PatientSupplier().get();
        LOG.trace(abstractCustomer.getName() + " is now in line");
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
            LOG.trace("The next customer is: " + customer.getName());
        }
        return customer;
    }

    public int getLineLength() {
        int length = size();
        LOG.trace("There are " + length + " people in line");
        return length;
    }

    public void getCustomerLineInformation() {
        Iterator<AbstractCustomer> iterator = this.iterator();
        List<String> names = new ArrayList<>();

        while (iterator.hasNext()) {
            AbstractCustomer person = iterator.next();
            names.add(person.getName());
        }
        LOG.info("Line: " + names);
    }

    public boolean isCustomerPatient(AbstractCustomer customer) {
        return customer instanceof Patient;
    }
}
