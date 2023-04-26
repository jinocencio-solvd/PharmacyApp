package Exceptions;

/**
 * Thrown to indicate that the requested quantity of a product is not available in the inventory.
 * This exception is typically thrown when a user tries to retrieve more of a product than the
 * quantity available in the inventory.
 */
public class InsufficientQuantityException extends Exception {

    /**
     * Constructs a new InsufficientQuantityException with the specified detail message.
     * @param message the detail message.
     */
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
