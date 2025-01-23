/**
 * A custom exception class for the IntroBlaise bot.
 * The {@code IntroBlaiseException} class is used to represent application-specific errors
 * that may occur during the execution of the IntroBlaise program.
 */
public class IntroBlaiseException extends Exception {

    /**
     * Constructs a new IntroBlaiseException with the specified detail message.
     * @param exception The detail message that explains the reason for this exception.
     */
    public IntroBlaiseException(String exception) {
        super(exception);
    }
}

