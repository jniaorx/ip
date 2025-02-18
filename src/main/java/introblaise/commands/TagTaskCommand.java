package introblaise.commands;

import introblaise.exceptions.EmptyLabelException;
import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;

/**
 * The {@code TagTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "tag" command. This command adds a tag
 * (label) to a specified task in the task list.
 */
public class TagTaskCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code TagTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} containing the tasks.
     */
    public TagTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "tag" command. This method parses the user input to extract
     * the task number (index) and the tag label, retrieves the corresponding
     * task, adds the tag to the task, saves the updated task list, and then
     * builds a formatted string to inform the user about the update.
     *
     * @param userInput The user input string, expected in the format "tag x y",
     *                  where 'x' is the index (starting from 1) of the task to
     *                  be tagged, and 'y' is the tag label.
     * @return A string message confirming that the task has been tagged, or an
     *         error message if the input is invalid (e.g., invalid index, missing
     *         label, task already tagged).
     * @throws IndexOutOfBoundsException If the provided task index is out of range.
     * @throws NumberFormatException   If the task index cannot be parsed as a number.
     */
    @Override
    public String execute(String userInput) {
        try {
            int taskNo = extractTaskNo(userInput);

            Task currTask = retrieveTask(taskNo);
            String label = extractLabel(userInput);

            if (currTask.getIsTagged()) {
                return "This task is already tagged!";
            }

            setTag(currTask, label);
            saveTask();
            return "I've tagged this task: " + currTask;
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Have you entered the correct index?";
        } catch (EmptyLabelException | InvalidInputException e) {
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
     * Extracts the tag label from the user input.
     *
     * @param userInput The user input string.
     * @return The tag label.
     * @throws EmptyLabelException If the tag label is empty.
     */
    private String extractLabel(String userInput) throws EmptyLabelException, InvalidInputException {
        return UtilParser.parseTagLabel(userInput);
    }

    /**
     * Sets the tag (label) for the specified task.
     *
     * @param currTask The {@link Task} object to add the tag to.
     * @param label    The tag label to set.
     */
    private void setTag(Task currTask, String label) {
        currTask.setTag(label);
    }

    /**
     * Saves the updated task list to storage.
     */

    private void saveTask() {
        taskList.saveTasks();
    }
}
