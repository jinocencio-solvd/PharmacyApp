package inventory;

import exceptions.InsufficientQuantityException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import java.util.HashMap;
import product.*;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents an inventory of products with their quantities.
 */
public abstract class Inventory implements IInventory {

    private static final Logger LOG = LogManager.getLogger(Inventory.class);
    private final Map<Product, Integer> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    /**
     * Adds a product to the inventory with the specified quantity.
     *
     * @param product  the product to be added to the inventory
     * @param quantity the quantity of the product to be added
     */
    public void addProduct(Product product, int quantity) {
        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
        LOG.trace("Added " + product.getName() + " to inventory or cart");
    }

    /**
     * Removes a product from the inventory with the specified quantity.
     *
     * @param product  the product to be removed from the inventory
     * @param quantity the quantity of the product to be removed
     */
    public void removeProduct(Product product, int quantity)
        throws InsufficientQuantityException, ProductDoesNotExistException, ProductOutOfStockException {

        int currentQuantity = products.getOrDefault(product, 0);
        if (!products.containsKey(product)) {
            throw new ProductDoesNotExistException(
                "The product, " + product + ", is not included in the inventory.");
        }
        if (currentQuantity == 0) {
            throw new ProductOutOfStockException(
                "product " + product + " is out of stock.");
        }
        if (quantity > currentQuantity) {
            throw new InsufficientQuantityException(
                "The product, " + product + ", is limited to a maximum quantity of "
                    + currentQuantity + " in stock.");
        }
        products.put(product, currentQuantity - quantity);
    }

    /**
     * Returns the quantity of a product in the inventory.
     *
     * @param product the product to get the quantity for
     * @return the quantity of the product in the inventory, or 0 if the product is not in the
     * inventory
     */
    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    @Override
    public String toString() {
        return "Inventory.Inventory{" +
            "products=" + products +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(products, inventory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
