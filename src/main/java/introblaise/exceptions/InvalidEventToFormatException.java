package introblaise.exceptions;

/**
 * Exception class representing an error condition where user inputs
 * incorrect format of "to" for an Event task.
 */
public class InvalidEventToFormatException extends IntroBlaiseException {
    /**
     * Constructs a new {@code InvalidEventToFormatException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public InvalidEventToFormatException(String exception) {
        super(exception);
    }
}
