package introblaise.commands;

import introblaise.exceptions.DeleteEmptyTaskListException;
import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;

/**
 * The {@code DeleteTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "delete" command. This command removes a
 * specific task from the task list based on its index.
 */
public class DeleteTaskCommand implements TaskCommand {
    private final TaskList taskList;
    /**
     * Constructs a {@code DeleteTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} from which tasks will be deleted.
     */
    public DeleteTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "delete" command. This method parses the user input to extract
     * the task number (index), retrieves the corresponding task from the task list,
     * removes the task, and then builds a formatted string to inform the user
     * about the deletion.
     *
     * @param userInput The user input string, expected in the format "delete x",
     *                  where 'x' is the index (starting from 1) of the task to be deleted.
     * @return A string message confirming the task deletion, or an error message
     *         if the input is invalid (e.g., invalid index, empty task list).
     * @throws IndexOutOfBoundsException    If the provided task index is out of range.
     * @throws NumberFormatException       If the task index cannot be parsed as a number.
     */
    @Override
    public String execute(String userInput) {
        try {
            if (taskList.getTasksList().isEmpty()) {
                throw new DeleteEmptyTaskListException("Your task list is empty. You can't delete anything. "
                        + "Please add tasks.");
            }
            int taskNo = extractTaskNo(userInput);
            Task currTask = getTaskByTaskNo(taskNo);
            deleteTask(currTask);
            return buildResponseString(currTask);
        } catch (DeleteEmptyTaskListException | InvalidInputException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Have you entered the index? Are you sure "
                    + "you are deleting the correct task? It should be in the format: delete [TASKNUMBER]";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'delete'."
                    + "It should be in the format: delete [TASKNUMBER]";
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
    private Task getTaskByTaskNo(int taskNo) {
        assert taskNo >= 0 : "Task number must be non-negative";
        assert taskNo < taskList.getSize() : "Task number must not exceed size of task list";
        return taskList.getTask(taskNo);
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param currTask The {@link Task} object to be removed.
     */
    private void deleteTask(Task currTask) {
        taskList.removeTask(currTask);
    }

    /**
     * Builds the formatted string response to inform the user about the task deletion.
     *
     * @param currTask The deleted {@link Task} object.
     * @return The formatted string response.
     */
    private String buildResponseString(Task currTask) {
        int numOfTask = taskList.getSize();
        String response = "Noted. I've removed this task: " + "\n" + currTask
                + "\n" + "Now you have " + numOfTask + " tasks in the list.";
        return response.trim();
    }
}
