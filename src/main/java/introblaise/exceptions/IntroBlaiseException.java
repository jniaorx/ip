package introblaise.exceptions;

/**
 * A custom exception class for the introBlaise.ui.IntroBlaise bot.
 * The {@code introBlaise.exceptions.IntroBlaiseException} class is used to represent application-specific errors
 * that may occur during the execution of the introBlaise.ui.IntroBlaise program.
 */
public class IntroBlaiseException extends Exception {

    /**
     * Constructs a new introBlaise.exceptions.IntroBlaiseException with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public IntroBlaiseException(String exception) {
        super(exception);
    }
}

