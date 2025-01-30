/**
 * The entry point for the IntroBlaise bot.
 */
public class IntroBlaise {
    /**
     * Main method to run the IntroBlaise bot.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Initialize UI
        Ui ui = new Ui();
        ui.showWelcome();

        // Initialize application components
        TaskManager taskManager = new TaskManager();
        Parser commands = new Parser(taskManager);

        // Main loop for user interaction
        while (true) {
            String userInput = ui.readInput();

            if (userInput.equalsIgnoreCase("Bye")) {
                ui.showGoodbye();
                break;
            }

            commands.executeCommand(userInput);
        }

        // Close scanner before exiting
        ui.closeScanner();
    }
}
