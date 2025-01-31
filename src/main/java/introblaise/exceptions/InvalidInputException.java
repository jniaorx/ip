package introblaise.exceptions;
/**
 * Exception class representing an error condition where user inputs
 * an invalid command.
 */
public class InvalidInputException extends IntroBlaiseException {
    /**
     * Constructs a new {@code introBlaise.exceptions.InvalidInputException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public InvalidInputException (String exception) {
        super(exception);
    }
}
