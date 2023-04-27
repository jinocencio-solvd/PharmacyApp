package exceptions;

/**
 * The `ProductOutOfStockException` exception is thrown when a product is out of stock in the inventory or
 * there are no product left in cart.
 */
public class ProductOutOfStockException extends Exception {

    public ProductOutOfStockException(String s) {
    }
}
