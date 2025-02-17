package introblaise.commands;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;
/**
 * The {@code RemoveTagCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "untag" or "removetag" command.  This command
 * removes the tag from a specified task in the task list.
 */
public class RemoveTagCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code RemoveTagCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} containing the tasks.
     */
    public RemoveTagCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "untag" or "removetag" command. This method parses the user input
     * to extract the task number (index), retrieves the corresponding task,
     * removes the tag from the task, saves the updated task list, and then builds
     * a formatted string to inform the user about the update.
     *
     * @param userInput The user input string, expected in the format "untag x" or
     *                  "removetag x", where 'x' is the index (starting from 1) of
     *                  the task to remove the tag from.
     * @return A string message confirming that the tag has been removed, or an
     *         error message if the input is invalid (e.g., invalid index, task
     *         is not tagged).
     * @throws IndexOutOfBoundsException If the provided task index is out of range.
     * @throws NumberFormatException       If the task index cannot be parsed as a number.
     */
    @Override
    public String execute(String userInput) {
        try {
            int taskNo = extractTaskNo(userInput);
            Task currTask = retrieveTask(taskNo);
            if (!currTask.getIsTagged()) {
                return "This task is not tagged.";
            }
            deleteTag(currTask);
            saveTask();
            return "Tag for " + currTask + " has successfully been deleted!";
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Have you entered the correct index?";
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses the task number (index) from the user input.
     *
     * @param userInput The user input string.
     * @return The task number (0-based index).
     * @throws NumberFormatException If the task number cannot be parsed as an integer.
     */
    private int extractTaskNo(String userInput) throws InvalidInputException {
        return UtilParser.parseTaskNumber(userInput);
    }

    /**
     * Retrieves the task from the task list based on its index.
     *
     * @param taskNo The index of the task (0-based).
     * @return The {@link Task} object at the specified index.
     * @throws IndexOutOfBoundsException If the task number is out of range.
     */
    private Task retrieveTask(int taskNo) {
        assert taskNo >= 0 : "Task number must be non-negative";
        assert taskNo < taskList.getTasksList().size() : "Task number must not exceed size of task list";
        return taskList.getTask(taskNo);
    }

    /**
     * Removes the tag from the specified task.
     *
     * @param currTask The {@link Task} object to remove the tag from.
     */
    private void deleteTag(Task currTask) {
        currTask.deleteTag();
    }

    /**
     * Saves the updated task list to storage.
     */
    private void saveTask() {
        taskList.saveTasks();
    }
}
