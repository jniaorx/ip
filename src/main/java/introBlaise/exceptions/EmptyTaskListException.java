package introBlaise.exceptions;

/**
 * Exception class representing an error condition where an attempt is made to print
 * an empty task list.
 */
public class EmptyTaskListException extends IntroBlaiseException {
    /**
     * Constructs a new {@code introBlaise.exceptions.EmptyTaskListException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public EmptyTaskListException(String exception) {
        super(exception);
    }
}
