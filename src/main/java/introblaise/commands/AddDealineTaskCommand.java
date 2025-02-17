package introblaise.commands;

import java.time.format.DateTimeParseException;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.DeadlineTaskParser;
import introblaise.task.TaskList;
import introblaise.tasktype.Deadline;

/**
 * Command class that handles the addition of a Deadline task to the task list.
 * This class implements the {@code TaskCommand} interface and performs the necessary
 * parsing and validation to add a {@code Deadline} task to the {@code TaskList}.
 */
public class AddDealineTaskCommand implements TaskCommand {
    private final TaskList taskList;
    private final DeadlineTaskParser deadlineTaskParser;

    /**
     * Constructs an {@code AddDeadlineTaskCommand} with the specified {@code TaskList}.
     * Initializes the {@code DeadlineTaskParser} for parsing deadline-related tasks.
     *
     * @param taskList The task list to which the deadline task will be added.
     */
    public AddDealineTaskCommand(TaskList taskList) {
        this.taskList = taskList;
        this.deadlineTaskParser = new DeadlineTaskParser();
    }

    /**
     * Executes the command to add a {@code Deadline} task to the task list.
     * The user input is expected to be in the format: "deadline [description] /by [date] [time]",
     * where [description] is the task description, and [date] is the due date in a specific format.
     *
     * @param userInput The input string from the user that contains the description and deadline date.
     *                  It should be in the format "deadline [description] /by [date] [time]".
     * @return A response message indicating whether the task was successfully added or if an error occurred.
     *         The message includes the details of the added task and the current number of tasks in the list.
     */
    @Override
    public String execute(String userInput) {
        try {
            Deadline deadlineTask = (Deadline) deadlineTaskParser.parse(userInput);
            addTaskToList(deadlineTask);
            return buildResponseString(deadlineTask);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds the provided {@code Deadline} task to the task list.
     * Also checks that the task list is not empty after adding the task.
     *
     * @param deadlineTask The deadline task to be added to the task list.
     */
    private void addTaskToList(Deadline deadlineTask) {
        taskList.addTask(deadlineTask);
        int numOfTask = taskList.getSize();
        assert numOfTask > 0 : "The task list should not be empty.";
    }

    /**
     * Builds a response string to indicate the successful addition of a deadline task.
     * The response includes the details of the task and the current number of tasks in the list.
     *
     * @param deadlineTask The deadline task that was added.
     * @return A string response indicating the success of the task addition, formatted with task details.
     */
    private String buildResponseString(Deadline deadlineTask) {
        StringBuilder response = new StringBuilder();
        int numOfTask = taskList.getSize();
        response.append("Got it. I've added this task: ").append("\n").append(deadlineTask).append("\n")
                .append("Now you have ").append(numOfTask).append(" tasks in the list.");
        return response.toString().trim();
    }
}
