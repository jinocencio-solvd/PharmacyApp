import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an inventory of products with their quantities.
 */
public class Inventory implements IInventory {

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
    }

    /**
     * Removes a product from the inventory with the specified quantity.
     *
     * @param product  the product to be removed from the inventory
     * @param quantity the quantity of the product to be removed
     */
    public void removeProduct(Product product, int quantity) {
        // TODO: Make method more robust in handling cases
        // 1. requested quantity is less than current quantity
        // 2. Product does not exist in inventory
        int currentQuantity = products.get(product);
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
        return "Inventory{" +
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
