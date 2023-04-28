package exceptions;

/**
 * An exception thrown when a prescription has no more refills available.
 */
public class NoMoreRefillsException extends Exception {

    public NoMoreRefillsException(String message) {
        super(message);
    }
}
