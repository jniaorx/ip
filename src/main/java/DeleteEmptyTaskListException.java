/**
 * Exception class representing an error condition where an attempt is made to delete a task from an
 * empty task list.
 *
 */
public class DeleteEmptyTaskListException extends IntroBlaiseException {
    /**
     * Constructs a new {@code DeleteEmptyTaskListException} with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public DeleteEmptyTaskListException(String exception) {
        super(exception);
    }
}
