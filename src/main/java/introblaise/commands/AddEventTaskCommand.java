package introblaise.commands;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.EventTaskParser;
import introblaise.task.TaskList;
import introblaise.tasktype.Event;

/**
 * Command class that handles the addition of an Event task to the task list.
 * This class implements the {@code TaskCommand} interface and performs the necessary
 * parsing and validation to add a {@code Event} task to the {@code TaskList}.
 */
public class AddEventTaskCommand implements TaskCommand {
    private final TaskList taskList;
    private final EventTaskParser eventTaskParser;

    /**
     * Constructs an {@code AddEventTaskCommand} with the specified {@code TaskList}.
     * Initializes the {@code EventTaskParser} for parsing event-related tasks.
     *
     * @param taskList The task list to which the event task will be added.
     */
    public AddEventTaskCommand(TaskList taskList) {
        this.taskList = taskList;
        this.eventTaskParser = new EventTaskParser();
    }
    /**
     * Executes the command to add a {@code Event} task to the task list.
     * The user input is expected to be in the format: "event [description] /from [date] [time] /to [date] [time]",
     * where [description] is the task description, and [date] is the event duration in a specific format.
     *
     * @param userInput The input string from the user that contains the description and deadline date.
     *                  It should be in the format "event [description] /from [date] [time] /to [date] [time]".
     * @return A response message indicating whether the task was successfully added or if an error occurred.
     *         The message includes the details of the added task and the current number of tasks in the list.
     */
    @Override
    public String execute(String userInput) {
        try {
            Event eventTask = (Event) eventTaskParser.parse(userInput);
            addTaskToList(eventTask);
            return buildResponseString(eventTask);
        } catch (StringIndexOutOfBoundsException e) {
            return "Please enter a description and a duration for your task in the format: "
                    + "event [DESCRIPTION] /from [dd-mm-yyyy HHmm] /to [dd-mm-yyyy HHmm]";
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds the provided {@code Event} task to the task list.
     * Also checks that the task list is not empty after adding the task.
     *
     * @param eventTask The event task to be added to the task list.
     */
    private void addTaskToList(Event eventTask) {
        taskList.addTask(eventTask);
        int numOfTask = taskList.getSize();
        assert numOfTask > 0 : "The task list should not be empty.";
    }
    /**
     * Builds a response string to indicate the successful addition of an event task.
     * The response includes the details of the task and the current number of tasks in the list.
     *
     * @param eventTask The event task that was added.
     * @return A string response indicating the success of the task addition, formatted with task details.
     */
    private String buildResponseString(Event eventTask) {
        int numOfTask = taskList.getSize(); // no of task in task list
        assert numOfTask > 0 : "The task list should not be empty.";
        String response = "Got it. I've added this task: " + "\n" + eventTask
                + "\n" + "Now you have " + numOfTask + " tasks in the list.";
        return response.trim();
    }
}
