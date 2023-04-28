package exceptions;

/**
 * Exception thrown when a requested product does not exist in the inventory.
 */
public class ProductDoesNotExistException extends Exception {

    public ProductDoesNotExistException(String s) {
        super(s);
    }
}
