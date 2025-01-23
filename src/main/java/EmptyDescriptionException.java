/**
 * Exception class representing an error condition where an attempt is made to add
 * a task without description.
 */
public class EmptyDescriptionException extends IntroBlaiseException{
    /**
     * Constructs a new {@code EmptyDescriptionException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public EmptyDescriptionException(String exception) {
        super(exception);
    }
}
