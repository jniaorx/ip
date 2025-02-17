package introblaise.ui;

import java.util.Scanner;

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
     * Greets the user and captures their name.
     * This method prompts the user for their name and responds with a personalized
     * greeting. A {@link Scanner} object is used to read the user's input.
     *
     * @param scanner A {@code Scanner} object to read user input.
     */
    public void greet(Scanner scanner) {
        System.out.println("    _________________________________");
        System.out.println("    Sup! I am IntroBlaise. What's your name?");
        if (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            System.out.println("    _________________________________");
            System.out.println("    Hi " + name + "!(ʘ‿ʘ)╯ Nice to meet you! "
                    + "You can start adding tasks to your very own task list! :)");
            System.out.println("    _________________________________");
        }
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
