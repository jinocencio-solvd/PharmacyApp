package setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import person.Customer;

public class CustomerSupplier implements Supplier<Customer> {

    private static final int CUSTOMER_MAX_BALANCE = 1000;

    private static List<Customer> customerList = new ArrayList<>(
        List.of(DataProvider.predefinedCustomers()));


    @Override
    public Customer get() {
        if (customerList.size() == 0) {
            customerList = new ArrayList<>(
                List.of(DataProvider.predefinedCustomers()));
        }
        int randomInt = new Random().nextInt(customerList.size());
        Customer randomCustomer = customerList.get(randomInt);
        randomCustomer.setCreditBalance(CUSTOMER_MAX_BALANCE);
        customerList.remove(randomCustomer);
        return randomCustomer;
    }
}
