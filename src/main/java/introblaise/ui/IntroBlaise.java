package introblaise.ui;

import introblaise.task.TaskList;

/**
 * The entry point for the IntroBlaise bot.
 */
public class IntroBlaise {
    /**
     * Main method to run the IntroBlaise bot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        /*
        // Initialize UI
        Ui ui = new Ui();
        ui.showWelcome();

        // Initialize application components
        TaskList taskList = new TaskList();
        Parser commands = new Parser(taskList);

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
        */
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user input, typically a command to be processed.
     * @return A string response after the command has been executed.
     */
    public String getResponse(String input) {
        TaskList taskList = new TaskList();
        Parser commands = new Parser(taskList);

        return commands.executeCommand(input);
    }
}
