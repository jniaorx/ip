package introblaise.exceptions;

/**
 * The {@code EmptyDateException} class represents an exception that is thrown
 * when a required date input is missing or empty. This exception is typically
 * used when parsing user commands that require a date argument.
 */
public class EmptyDateException extends IntroBlaiseException {

    /**
     * Constructs a new {@code EmptyDateException} with the specified detail message.
     *
     * @param exception The detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public EmptyDateException(String exception) {
        super(exception);
    }
}
