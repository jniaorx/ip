package introblaise.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import introblaise.exceptions.EmptyDateException;
import introblaise.exceptions.EmptyLabelException;
import introblaise.exceptions.InvalidInputException;

/**
 * The {@code UtilParser} class provides utility methods for parsing various
 * components of user input, such as task numbers, keywords, dates, and command names.
 * These methods are used by other parser classes and command handlers to extract
 * and validate specific parts of the user's input.
 */
public class UtilParser {
    /**
     * Parses the task number from the user input. The task number is expected to be
     * the second word in the input string.
     *
     * @param userInput The user input string.
     * @return The task number (index-based, starting from 0).
     * @throws NumberFormatException If the task number cannot be parsed as an integer.
     * @throws IndexOutOfBoundsException If the user input does not contain a task number.
     */
    public static int parseTaskNumber(String userInput) {
        String[] parts = userInput.split(" ");
        String tasNoStr = parts[1];
        int taskNo = Integer.parseInt(tasNoStr) - 1;
        return taskNo;
    }

    /**
     * Parses the keyword for the find command from the user input.
     * The keyword is expected to be the substring after "find".
     *
     * @param userInput The user input string.
     * @return The keyword to search for.
     * @throws InvalidInputException If no keyword is provided after "find".
     */
    public static String parseFindKeyword(String userInput) throws InvalidInputException {
        String keyword = userInput.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new InvalidInputException("Please provide a keyword to search.");
        }
        return keyword;
    }

    /**
     * Parses a date string from the user input.
     *
     * @param userInput The user input string.
     * @return The date string.
     * @throws EmptyDateException If no date is provided.
     */
    public static String parseStringDateTime(String userInput) throws EmptyDateException {
        String date = userInput.substring(9).trim();
        if (date.isEmpty()) {
            throw new EmptyDateException("Please enter a date!");
        }
        return date;
    }

    /**
     * Parses a date string in the format "d-MM-yyyy" into a {@link LocalDate} object.
     *
     * @param dateString The date string in "d-MM-yyyy" format.
     * @return A {@link LocalDate} object representing the parsed date.
     */
    public static LocalDate convertDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate formattedDate = LocalDate.parse(dateString, formatter);
        return formattedDate;
    }

    /**
     * Converts a date and time string in the format "d-MM-yyyy HHmm" into a {@link LocalDateTime} object.
     *
     * @param dateTimeStr The date and time string in "d-MM-yyyy HHmm" format.
     * @return A {@link LocalDateTime} object representing the parsed date and time.
     */
    public static LocalDateTime convertFormattedDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
        LocalDateTime formattedDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        return formattedDateTime;
    }

    /**
     * Formats a {@link LocalDateTime} object into a string representation
     * using the format "MMM dd yyyy HHmm".
     *
     * @param formattedDateTime The {@link LocalDateTime} object to format.
     * @return The formatted date and time string.
     */
    public static String convertStringDateTimeFromFormatted(LocalDateTime formattedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return formattedDateTime.format(formatter);
    }

    /**
     * Parses the tag label from the user input.
     * The expected format is "tag [taskNumber] [label]".
     *
     * @param userInput The user input string.
     * @return The tag label.
     * @throws EmptyLabelException If no label is provided.
     */
    public static String parseTagLabel(String userInput) throws EmptyLabelException {
        String[] parts = userInput.split(" ", 3);
        if (parts.length != 3) {
            return "Invalid input format. Use 'tag <task_number> <label>'";
        }
        String label = parts[2].trim();
        if (label.isEmpty()) {
            throw new EmptyLabelException("Please enter a label for your tag!");
        }
        return label;
    }
    /**
     * Parses the command name from the user input. The command name is expected to be
     * the first word in the input string.
     *
     * @param userInput The user input string.
     * @return The command name (in lowercase and trimmed).
     */
    public static String parseCommand(String userInput) {
        String[] parts = userInput.split(" ");
        String command = parts[0].toLowerCase().trim();
        return command;
    }
}
