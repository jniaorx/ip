import java.util.Scanner;

/**
 * The entry point for the IntroBlaise bot.
 * The {@code IntroBlaise} class initializes the bot, displays a welcome message,
 * and continuously interacts with the user to execute commands or end the session.
 */
public class IntroBlaise {
    /**
     * Main method to run the IntroBlaise bot.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // ASCII art logo for the bot.
        String logo =
                ".___        __               __________.__         .__\n" +
                        "|   | _____/  |________  ____\\______   \\  | _____  |__| ______ ____\n" +
                        "|   |/    \\   __\\_  __ \\/  _ \\|    |  _/  | \\__  \\ |  |/  ___// __ \\\n" +
                        "|   |   |  \\  |  |  | \\(  <_> )    |   \\  |__/ __ \\|  |\\___ \\\\  ___/\n" +
                        "|___|___|  /__|  |__|   \\____/|______  /____(____  /__/____  >\\___  >\n" +
                        "\n";

        // Print welcome message with logo.
        System.out.println("Hello from\n" + logo);

        // Initialize application components.
        TaskManager taskManager = new TaskManager(); // Manage tasks.
        Commands commands = new Commands(taskManager); // Processes user commands.
        Greetings greetings = new Greetings(); // Handles user greetings and farewells.

        // Initialize scanner to read user input.
        Scanner scanner = new Scanner(System.in);

        // Greet the user.
        greetings.greet(scanner);

        // Main loop for user interaction.
        while(true) {
            // Read user input.
            String userInput = scanner.nextLine();

            // Check if the user wants to exit the bot.
            if (userInput.equalsIgnoreCase("Bye")) {
                // Bid farewell and exit the loop.
                greetings.sayBye();
                break;
            }

            // Execute the command input by the user.
            commands.executeCommand(userInput);
        }

        // Close the scanner to release resources.
        scanner.close();
    }
}
