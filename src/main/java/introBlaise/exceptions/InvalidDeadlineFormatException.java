package introBlaise.exceptions;

/**
 * Exception class representing an error condition where user inputs
 * incorrect format for a introBlaise.task.Deadline task.
 */
public class InvalidDeadlineFormatException extends IntroBlaiseException {
    /**
     * Constructs a new {@code InvalidDeadlineException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public InvalidDeadlineFormatException(String exception) {
        super(exception);
    }
}
