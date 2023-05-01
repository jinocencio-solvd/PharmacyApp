package inventory;

import java.util.LinkedHashMap;
import person.Customer;
import java.util.Objects;

public class Cart extends Inventory {

    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
        this.products = new LinkedHashMap<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Inventory.Cart{" +
            "customer=" + customer +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Cart cart = (Cart) o;
        return Objects.equals(getCustomer(), cart.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomer());
    }
}
