package introblaise.parsers;

import introblaise.exceptions.InvalidInputException;
import introblaise.task.Task;
import introblaise.tasktype.ToDo;

/**
 * The {@code ToDoTaskParser} class is responsible for parsing user input
 * to create {@link ToDo} tasks. It implements the {@link TaskParser} interface
 * and handles the specific format required for todo task creation.
 */
public class ToDoTaskParser implements TaskParser {
    /**
     * Parses the user input to create a {@link ToDo} task.
     * The expected format of the user input is:
     * {@code todo x}, where 'x' is the task description.
     * <p>
     * Example: {@code todo Buy groceries}
     *
     * @param userInput The user input string.
     * @return A {@link ToDo} task object created from the parsed input.
     * @throws InvalidInputException If the user input does not conform to the
     *                                 expected format, or if the description
     *                                 is missing or empty.
     */
    @Override
    public Task parse(String userInput) throws InvalidInputException {
        String description = parseDescription(userInput);
        return new ToDo(description);
    }
    /**
     * Parses the task description from the user input.
     * The description is extracted from the substring after "todo".
     *
     * @param userInput The user input string.
     * @return The task description.
     * @throws InvalidInputException If the description is empty or not found.
     */
    private String parseDescription(String userInput) throws InvalidInputException {
        String description = userInput.substring(5).trim();
        assert (!description.isEmpty()) : "The description should not be empty.";
        if (description.isEmpty()) {
            throw new InvalidInputException("Please enter a description for your todo task!"
                    + "It should be in the format: todo [DESCRIPTION]");
        }
        return description;
    }
}
