package introblaise.commands;

import introblaise.task.TaskList;

/**
 * The {@code ClearCommand} class implements the {@link TaskCommand} interface
 * and is responsible for clearing the task list. This command removes everything
 * from the task list.
 */
public class ClearCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code ClearCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} to be deleted.
     */
    public ClearCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "clear" command. This method clears all tasks from the task list.
     *
     * @param userInput The user input string, expected in the format "clear".
     * @return A string message confirming the task list has been cleared.
     */
    @Override
    public String execute(String userInput) {
        taskList.clearTaskList();
        return "Yay! Your task list has been cleared!";
    }
}
