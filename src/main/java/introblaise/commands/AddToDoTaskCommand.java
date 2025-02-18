package introblaise.commands;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.ToDoTaskParser;
import introblaise.task.TaskList;
import introblaise.tasktype.ToDo;

/**
 * Command class that handles the addition of a ToDo task to the task list.
 * This class implements the {@code TaskCommand} interface and performs the necessary
 * parsing and validation to add a {@code ToDo} task to the {@code TaskList}.
 */
public class AddToDoTaskCommand implements TaskCommand {
    private final TaskList taskList;
    private final ToDoTaskParser toDoTaskParser;

    /**
     * Constructs an {@code AddToDoTaskCommand} with the specified {@code TaskList}.
     * Initializes the {@code ToDoParser} for parsing todo-related tasks.
     *
     * @param taskList The task list to which the todo task will be added.
     */
    public AddToDoTaskCommand(TaskList taskList) {
        this.taskList = taskList;
        this.toDoTaskParser = new ToDoTaskParser();
    }

    /**
     * Executes the command to add a {@code ToDo} task to the task list.
     * The user input is expected to be in the format: "todo [description]",
     * where [description] is the task description.
     *
     * @param userInput The input string from the user that contains the description.
     *                  It should be in the format "todo [description]".
     * @return A response message indicating whether the task was successfully added or if an error occurred.
     *         The message includes the details of the added task and the current number of tasks in the list.
     */
    @Override
    public String execute(String userInput) {
        try {
            ToDo todoTask = (ToDo) toDoTaskParser.parse(userInput);
            addTaskToList(todoTask);
            return buildResponseString(todoTask);
        } catch (StringIndexOutOfBoundsException e) {
            return "Errr...Please enter a description...It should be in the format: todo [DESCRIPTION]";
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds the provided {@code ToDo} task to the task list.
     * Also checks that the task list is not empty after adding the task.
     *
     * @param toDoTask The todo task to be added to the task list.
     */
    private void addTaskToList(ToDo toDoTask) {
        taskList.addTask(toDoTask);
        int numOfTask = taskList.getTasksList().size();
        assert numOfTask > 0 : "The task list should not be empty.";
    }

    /**
     * Builds a response string to indicate the successful addition of a todo task.
     * The response includes the details of the task and the current number of tasks in the list.
     *
     * @param toDoTask The todo task that was added.
     * @return A string response indicating the success of the task addition, formatted with task details.
     */
    private String buildResponseString(ToDo toDoTask) {
        int numOfTask = taskList.getSize();
        String response = "Got it. I've added this task: " + "\n" + toDoTask + "\n"
                + "Now you have " + numOfTask + " tasks in the list.";
        return response.trim();
    }
}
