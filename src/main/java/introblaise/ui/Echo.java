package introblaise.ui;

import java.util.Scanner;

/**
 * A class that echoes user input until the user types "bye".
 * This class provides a method to interact with the user via the console,
 * repeatedly printing back whatever the user inputs unless the input is "bye".
 */
public class Echo {
    /**
     * Default constructor or the {@code introBlaise.ui.Echo} class.
     * Initializes an {@code introBlaise.ui.Echo} instance. This constructor does not perform
     * any specific initialization as no fields are defined for the class.
     */
    public Echo() {
    }

    /**
     * Starts an interactive session that echoes user input until the user types "bye".
     * This method reads input from the provided {@link Scanner} object and prints
     * it back to the console in a formatted manner. The interaction ends when the
     * user inputs the word "bye",
     *
     * @param scanner A {@link Scanner} object used to read input from the user.
     */
    public void echo(Scanner scanner) {
        // Wait for the first user input.
        String userInput = scanner.nextLine();

        // Continue echoing user input until "bye" is entered.
        while (!userInput.equals("bye")) {
            // Print a formatted response with the user input
            System.out.println("    __________________");
            System.out.println("    " + userInput);
            System.out.println("    __________________");

            // Wait for the next user input
            userInput = scanner.nextLine();
        }
    }
}
