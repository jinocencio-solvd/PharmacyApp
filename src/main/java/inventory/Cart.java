package inventory;

import java.util.LinkedHashMap;

public class Cart extends Inventory {

    public Cart() {
        this.products = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return "Inventory.Cart{" +
            super.toString() + "} ";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
