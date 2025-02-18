package introblaise.commands;

/**
 * The {@code HelpCommand} class provides a list of available commands
 * for the IntroBlaise task management application.
 *
 * This command helps users understand the valid inputs they can use
 * to interact with the application.
 */
public class HelpCommand implements TaskCommand {
    /**
     * Executes the help command and returns a list of valid commands.
     *
     * @param userInput The user input (not used in this command).
     * @return A formatted string containing a list of valid commands.
     */
    @Override
    public String execute(String userInput) {
        return helpCommands();
    }

    /**
     * Returns a formatted list of all valid commands for the application.
     *
     * @return A string containing the list of valid commands.
     */
    private String helpCommands() {
        return "Hi! Here are the valid commands for IntroBlaise:\n"
                + "1. todo [DESCRIPTION]: Add a To-Do task\n"
                + "2. deadline [DESCRIPTION] /by [dd-mm-yyyy HHmm]: Add a Deadline task\n"
                + "3. event [DESCRIPTION] /from [dd-mm-yyyy HHmm] /to [dd-mm-yyyy HHmm]: Add an Event task\n"
                + "4. list: View all tasks\n"
                + "5. mark [TASKINDEX]: Mark a task as done\n"
                + "6. unmark [TASKINDEX]: Unmark a task as not done\n"
                + "7. delete [TASKINDEX]: Delete a task\n"
                + "8. tasks on [dd-mm-yyyy]: Search tasks by date\n"
                + "9. find [KEYWORD]: Search tasks by keyword\n"
                + "10. tag [TASKINDEX] [LABEL]: Add a tag to a task\n"
                + "11. untag [TASKINDEX]: Remove a tag from a task\n"
                + "12. clear: Clears the entire task list\n"
                + "13. help: Show this help message";
    }
}
