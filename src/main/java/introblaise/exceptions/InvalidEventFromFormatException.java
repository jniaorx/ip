package introblaise.exceptions;

/**
 * Exception class representing an error condition where user inputs
 * incorrect format of "from" for an introBlaise.task.Event task.
 */
public class InvalidEventFromFormatException extends IntroBlaiseException {
    /**
     * Constructs a new {@code introBlaise.exceptions.InvalidEventFromFormatException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public InvalidEventFromFormatException(String exception) {
        super(exception);
    }
}
