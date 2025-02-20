package introblaise.parsers;

import java.time.format.DateTimeParseException;

import introblaise.exceptions.InvalidInputException;
import introblaise.task.Task;
import introblaise.tasktype.Deadline;

/**
 * The {@code DeadlineTaskParser} class is responsible for parsing user input
 * to create {@link Deadline} tasks. It implements the {@link TaskParser} interface
 * and handles the specific format required for deadline task creation.
 */
public class DeadlineTaskParser implements TaskParser {
    /**
     * Parses the user input to create a {@link Deadline} task.
     * The expected format of the user input is:
     * {@code deadline x /by date}, where 'x' is the task description and 'date'
     * is the deadline date and time.
     * <p>
     * Example: {@code deadline Finish project /by 2024-03-15 23:59}
     *
     * @param userInput The user input string.
     * @return A {@link Deadline} task object created from the parsed input.
     * @throws InvalidInputException If the user input does not conform to the
     *                                 expected format, or if required information
     *                                 (description or deadline) is missing.  Specifically,
     *                                 this exception is thrown if the description is empty
     *                                 or if the /by date is missing.
     */
    @Override
    public Task parse(String userInput) throws InvalidInputException {
        String description = parseDescription(userInput);
        String dateTime = parseDateTime(userInput);

        return new Deadline(description, dateTime);
    }

    /**
     * Parses the task description from the user input.
     * The description is extracted from the substring after "deadline" and before "/by".
     *
     * @param userInput The user input string.
     * @return The task description.
     * @throws InvalidInputException If the description is empty or not found in the correct location.
     */
    private String parseDescription(String userInput) throws InvalidInputException {
        try {
            String description = userInput.substring(8, userInput.indexOf("/")).trim();
            assert (!description.isEmpty()) : "The description should not be empty."
                    + "It should be in the format: deadline [DESCRIPTION] /by [dd-mm-yyyy] [HHmm]";

            if (description.isEmpty()) {
                throw new InvalidInputException("Please enter a description for your deadline task."
                        + "It should be in the format: deadline [DESCRIPTION] /by [dd-mm-yyyy] [HHmm]");
            }
            return description;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidInputException("Please enter a description for your deadline task."
                    + "It should be in the format: deadline [DESCRIPTION] /by [dd-mm-yyyy] [HHmm]");
        }
    }

    /**
     * Parses the deadline date and time from the user input.
     * The date and time are extracted from the substring after "/by".
     *
     * @param userInput The user input string.
     * @return The deadline date and time string.
     * @throws InvalidInputException If the deadline date and time are missing or empty.
     */
    private String parseDateTime(String userInput) throws InvalidInputException {
        try {
            int separator = userInput.indexOf("/") + 4;
            String dateTime = userInput.substring(separator).trim();
            if (dateTime.isEmpty()) {
                throw new InvalidInputException("There seems to be no deadline entered...? "
                        + "Please enter a deadline after the word /by."
                        + " It should be in the format: deadline [DESCRIPTION] /by [dd-mm-yyyy] [HHmm]");
            }
            try {
                UtilParser.convertFormattedDateTime(dateTime);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid date-time format: \"" + dateTime
                        + "\". Please input your date and time in the format: d-MM-yyyy HHmm.");
            }

            return dateTime;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidInputException("Please input in the format of "
                    + "deadline [DESCRIPTION] /by [dd-mm-yyyy] [HHmm]");
        }

    }
}
