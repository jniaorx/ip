package introblaise.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import introblaise.exceptions.EmptyDateException;
import introblaise.parsers.UtilParser;
import introblaise.task.TaskList;

/**
 * The {@code GetTasksOnDateCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "tasks on" command. This command retrieves
 * and displays tasks from the task list that are scheduled for a specific date.
 */
public class GetTasksOnDateCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code GetTasksOnDateCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} to retrieve tasks from.
     */
    public GetTasksOnDateCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "tasks on" command. This method parses the user input to extract
     * the date, formats it, retrieves the tasks scheduled for that date from the
     * task list, and then builds a formatted string containing the list of tasks.
     *
     * @param userInput The user input string, expected in the format "tasks on d-MM-yyyy".
     * @return A string containing the list of tasks scheduled for the specified date,
     *         or an error message if the input is invalid (e.g., invalid date format,
     *         missing date).
     * @throws DateTimeParseException If the date part of the user input cannot be parsed
     *                                  as a date in the "d-MM-yyyy" format.
     * @throws EmptyDateException     If no date is provided after "tasks on".
     */
    @Override
    public String execute(String userInput) {
        try {
            String taskDateStr = extractDateString(userInput);
            LocalDate formattedDate = convertDateStr(taskDateStr);

            return getTasksForDate(formattedDate);
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please enter the date in d-MM-yyyy format.";
        } catch (EmptyDateException e) {
            return e.getMessage();
        }
    }

    /**
     * Extracts the date string from the user input.
     *
     * @param userInput The user input string.
     * @return The date string.
     * @throws EmptyDateException If no date is provided.
     */
    private String extractDateString(String userInput) throws EmptyDateException {
        return UtilParser.parseStringDateTime(userInput);
    }

    /**
     * Convert the date string into a {@link LocalDate} object.
     *
     * @param dateString The date string.
     * @return A {@link LocalDate} object representing the parsed date.
     */
    private LocalDate convertDateStr(String dateString) {
        return UtilParser.convertDateString(dateString);
    }

    /**
     * Retrieves and formats the tasks for the specified date.
     *
     * @param formattedDate The date for which to retrieve tasks.
     * @return A string containing the formatted list of tasks.
     */
    private String getTasksForDate(LocalDate formattedDate) {
        return taskList.printTasksForDate(formattedDate);
    }
}
