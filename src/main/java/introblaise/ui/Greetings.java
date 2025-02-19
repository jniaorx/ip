package introblaise.ui;

/**
 * Handles user greetings and farewells for the bot.
 * The {@code Greetings} class provides methods to greet the user when
 * they start interacting with the application and bid them farewell
 * when they exit.
 */

public class Greetings {
    /**
     * Constructs a {@code Greetings} object.
     * This constructor does not perform any initialization.
     */
    public Greetings() {
    }

    /**
     * Bids farewell to the user.
     * This method prints a goodbye message to the user when they exit the application.
     */
    public void sayBye() {
        System.out.println("    _________________________________");
        System.out.println("    Bye! Hope to see you again soon! :(");
        System.out.println("    _________________________________");
    }
}
