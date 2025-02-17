package introblaise.commands;

import introblaise.task.TaskList;

/**
 * The {@code ListTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "list" command.
 * This command displays all tasks currently stored in the task list.
 */
public class ListTaskCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code ListTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} to retrieve tasks from.
     */
    public ListTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "list" command.
     * This method retrieves all tasks from the task list and formats them into a string for display to the user.
     *
     * @param userInput The user input string (which is ignored for this command).
     * @return A string containing the formatted list of tasks.
     */
    @Override
    public String execute(String userInput) {
        return taskList.printTaskList();
    }
}
