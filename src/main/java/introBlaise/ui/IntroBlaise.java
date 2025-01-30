package introBlaise.ui;

import introBlaise.task.TaskList;

/**
 * The entry point for the introBlaise.ui.IntroBlaise bot.
 */
public class IntroBlaise {
    /**
     * Main method to run the introBlaise.ui.IntroBlaise bot.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
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
    }
}
