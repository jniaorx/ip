package introblaise.parsers;

import introblaise.exceptions.InvalidInputException;
import introblaise.task.Task;
import introblaise.tasktype.Event;

/**
 * The {@code EventTaskParser} class is responsible for parsing user input
 * to create {@link Event} tasks. It implements the {@link TaskParser} interface
 * and handles the specific format required for event task creation.
 */
public class EventTaskParser implements TaskParser {
    /**
     * Parses the user input to create an {@link Event} task.
     * The expected format of the user input is:
     * {@code event x /from date /to date}, where 'x' is the task description,
     * 'date' after '/from' is the start date and time of the event, and 'date'
     * after '/to' is the end date and time of the event.
     * <p>
     * Example: {@code event Meeting with John /from 2024-03-15 1000 /to 2024-03-15 1200}
     *
     * @param userInput The user input string.
     * @return An {@link Event} task object created from the parsed input.
     * @throws InvalidInputException If the user input does not conform to the
     *                                 expected format, or if required information
     *                                 (description, from date/time, or to date/time)
     *                                 is missing or invalid. Specifically, this
     *                                 exception is thrown if the description is empty,
     *                                 if either the /from or /to dates are missing,
     *                                 or if they are empty.
     */
    @Override
    public Task parse(String userInput) throws InvalidInputException {
        String description = parseDescription(userInput);
        String from = parseFromDateTime(userInput);
        String to = parseToDateTime(userInput);

        return new Event(description, from, to);
    }

    /**
     * Parses the task description from the user input.
     * The description is extracted from the substring after "event" and before "/from".
     *
     * @param userInput The user input string.
     * @return The task description.
     * @throws InvalidInputException If the description is empty or not found in the correct location.
     */
    private String parseDescription(String userInput) throws InvalidInputException {
        String description = userInput.substring(5, userInput.indexOf("/")).trim();
        assert (!description.isEmpty()) : "The description should not be empty.";
        if (description.isEmpty()) {
            throw new InvalidInputException("Please enter a description for your task!");
        }
        return description;
    }

    /**
     * Parses the 'from' date and time from the user input.
     * The date and time are extracted from the substring after "/from".
     *
     * @param userInput The user input string.
     * @return The 'from' date and time string.
     * @throws InvalidInputException If the '/from' date and time are missing or empty.
     */
    private String parseFromDateTime(String userInput) throws InvalidInputException {
        if (!userInput.contains("/from")) {
            throw new InvalidInputException("Please include a 'From' date by using /from!");
        }
        String[] parts = userInput.split("/", 3);
        String from = parts[1].substring(4).trim();
        if (from.isEmpty()) {
            throw new InvalidInputException("Please include a 'From' date after the word /from");
        }
        return from;
    }

    /**
     * Parses the 'to' date and time from the user input.
     * The date and time are extracted from the substring after "/to".
     *
     * @param userInput The user input string.
     * @return The 'to' date and time string.
     * @throws InvalidInputException If the '/to' date and time are missing or empty.
     */
    private String parseToDateTime(String userInput) throws InvalidInputException {
        if (!userInput.contains("/to")) {
            throw new InvalidInputException("Please include a 'To' date by using /to!");
        }
        String[] parts = userInput.split("/", 3);
        String to = parts[2].substring(2).trim();
        if (to.isEmpty()) {
            throw new InvalidInputException("Please include a 'To' date after the word /to!");
        }
        return to;
    }
}
