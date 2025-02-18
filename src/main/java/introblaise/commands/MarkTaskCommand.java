package introblaise.commands;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;

/**
 * The {@code MarkTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "mark" command. This command marks a
 * specific task as done in the task list.
 */
public class MarkTaskCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code MarkTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} containing the tasks.
     */
    public MarkTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "mark" command. This method parses the user input to extract
     * the task number (index), retrieves the corresponding task from the task list,
     * marks the task as done, saves the updated task list, and then builds a
     * formatted string to inform the user about the update.
     *
     * @param userInput The user input string, expected in the format "mark x",
     *                  where 'x' is the index (starting from 1) of the task to be marked.
     * @return A string message confirming that the task has been marked as done,
     *         or an error message if the input is invalid (e.g., invalid index).
     * @throws IndexOutOfBoundsException If the provided task index is out of range.
     * @throws NumberFormatException       If the task index cannot be parsed as a number.
     */
    @Override
    public String execute(String userInput) {
        try {
            int taskNo = extractTaskNo(userInput);
            Task currTask = retrieveTask(taskNo);
            markAsDone(currTask);
            saveTask();
            return "Well done! I've marked this task as done: " + "\n" + currTask;
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Are you sure you are marking the correct task? It should be "
                    + "in the format: mark [TASKNUMBER]";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'unmark'."
                    + "It should be in the format: mark [TASKNUMBER]";
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
     * Marks the specified task as done.
     *
     * @param currTask The {@link Task} object to be marked as done.
     */
    private void markAsDone(Task currTask) {
        currTask.markAsDone();
    }

    /**
     * Saves the updated task list to storage.
     */
    private void saveTask() {
        taskList.saveTasks();
    }
}
