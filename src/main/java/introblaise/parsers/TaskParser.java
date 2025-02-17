package introblaise.parsers;

import introblaise.exceptions.InvalidInputException;
import introblaise.task.Task;

/**
 * The {@code TaskParser} interface defines the contract for classes that parse user input
 * and create {@link Task} objects.  Implementations of this interface are responsible for
 * handling the specific syntax and format of user commands for different task types
 * (e.g., ToDo, Deadline, Event).
 */
public interface TaskParser {
    /**
     * Parses the user input string and creates a {@link Task} object.
     * The specific format of the user input is determined by the implementing class.
     * This method is responsible for validating the input and extracting the necessary
     * information to create the appropriate {@link Task} instance.
     * <p>
     * Implementations should throw an {@link InvalidInputException} if the user input
     * is invalid or cannot be parsed. The exception message should provide specific
     * details about the error, such as missing arguments or incorrect formatting.
     *
     * @param userInput The user input string representing the task.
     * @return A {@link Task} object created from the parsed input.
     * @throws InvalidInputException If the user input is invalid or cannot be parsed.
     */
    Task parse(String userInput) throws InvalidInputException;
}
