package exceptions;

/**
 * An exception thrown when an employee is already registered into the employee database.
 */
public class DuplicatePersonException extends Exception {

    public DuplicatePersonException(String message) {
        super(message);
    }
}
