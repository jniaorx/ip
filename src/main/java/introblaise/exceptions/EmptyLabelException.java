package introblaise.exceptions;

/**
 * The {@code EmptyLabelException} class represents an exception that is thrown
 * when a required label input is missing or empty. This exception is typically
 * used when parsing user commands that require a label argument, such as
 * tagging a task.
 */
public class EmptyLabelException extends IntroBlaiseException {

    /**
     * Constructs a new {@code EmptyLabelException} with the specified detail message.
     *
     * @param exception The detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public EmptyLabelException(String exception) {
        super(exception);
    }
}
