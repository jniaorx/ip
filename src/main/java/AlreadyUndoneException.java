/**
 * Exception class representing an error condition where an attempt is made to mark a task
 * as "undone" when it has already been marked as not done.
 */
public class AlreadyUndoneException extends IntroBlaiseException{
    /**
     * Constructs a new {@code AlreadyUndoneException} with the specified detail message.
     * @param exception The detail message that explains the reson for this exception.
     */
    public AlreadyUndoneException(String exception) {
        super(exception);
    }
}
