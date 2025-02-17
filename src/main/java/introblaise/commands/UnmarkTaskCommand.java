package introblaise.commands;

import introblaise.exceptions.AlreadyUndoneException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;

/**
 * The {@code UnmarkTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "unmark" command. This command marks a
 * specific task as not done (undone) in the task list.
 */
public class UnmarkTaskCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code UnmarkTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} containing the tasks.
     */
    public UnmarkTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "unmark" command. This method parses the user input to extract
     * the task number (index), retrieves the corresponding task from the task list,
     * marks the task as undone, saves the updated task list, and then builds a
     * formatted string to inform the user about the update.
     *
     * @param userInput The user input string, expected in the format "unmark x",
     *                  where 'x' is the index (starting from 1) of the task to be unmarked.
     * @return A string message confirming that the task has been marked as undone,
     *         or an error message if the input is invalid (e.g., invalid index,
     *         task is already undone).
     * @throws IndexOutOfBoundsException If the provided task index is out of range.
     * @throws NumberFormatException       If the task index cannot be parsed as a number.
     */
    @Override
    public String execute(String userInput) {
        try {
            int taskNo = parseTaskNo(userInput);
            Task currTask = retrieveTask(taskNo);
            if (!currTask.getIsDone()) {
                throw new AlreadyUndoneException("This task has already been marked undone!");
            }
            markAsUndone(currTask);
            saveTask();
            return "OK, I've marked this task as not done yet: " + "\n" + currTask;
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Are you sure you are unmarking the correct task?";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'unmark'.";
        } catch (AlreadyUndoneException e) {
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
    private int parseTaskNo(String userInput) {
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
     * Marks the specified task as undone.
     *
     * @param currTask The {@link Task} object to be marked as undone.
     */
    private void markAsUndone(Task currTask) {
        currTask.markAsUndone();
    }

    /**
     * Saves the updated task list to storage.
     */
    private void saveTask() {
        taskList.saveTasks();
    }
}
