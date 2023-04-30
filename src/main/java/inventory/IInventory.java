package inventory;

import exceptions.InsufficientQuantityException;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductOutOfStockException;
import product.Product;

public interface IInventory {

    /**
     * Adds a product to the inventory with the specified quantity.
     *
     * @param product  the product to be added to the inventory
     * @param quantity the quantity of the product to be added
     */
    void addProduct(Product product, int quantity);

    /**
     * Removes a product from the inventory with the specified quantity.
     *
     * @param product  the product to be removed from the inventory
     * @param quantity the quantity of the product to be removed
     */
    void removeProduct(Product product, int quantity)
        throws InsufficientQuantityException, ProductDoesNotExistException, ProductOutOfStockException;

    /**
     * Returns the quantity of a product in the inventory.
     *
     * @param product the product to get the quantity for
     * @return the quantity of the product in the inventory, or 0 if the product is not in the
     * inventory
     */
    int getQuantity(Product product);
}
