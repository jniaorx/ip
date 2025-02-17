package introblaise.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;

import introblaise.parsers.UtilParser;
import introblaise.task.Task;

/**
 * Represents a task with a specific deadline that needs to be completed before a certain date or time.
 * This class extends the {@code Task} class and adds a deadline attribute to store the due date/time
 * of the task. It overrides the {@code toString()} method to provide a customized string representation.
 */
public class Deadline extends Task {
    private final String dateTimeStr;
    private final LocalDateTime formattedDatetime;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * The deadline is parsed from the provided string and stored as a {@code LocalDateTime} object.
     *
     * @param description The description of the task.
     * @param dateTimeStr The deadline (date/time) by which the task must be completed.
     */
    public Deadline(String description, String dateTimeStr) {
        super(description);
        this.dateTimeStr = dateTimeStr;
        this.formattedDatetime = getParsedFormattedDateTime(dateTimeStr);
    }

    /**
     * Converts the given String representation of a date and time to a {@code LocalDateTime} object.
     * This method utilizes a utility parser to interpret the string as a formatted date/time and return
     * it as a {@code LocalDateTime}, which is a more structured representation.
     *
     * @param dateTimeStr The String representing the date and time in a specific format.
     *                    The format should be compatible with the expected date-time format used by the parser.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     *         Returns {@code null} if the string cannot be parsed.
     */
    private LocalDateTime getParsedFormattedDateTime(String dateTimeStr) {
        return UtilParser.convertFormattedDateTime(dateTimeStr);
    }

    /**
     * Gets the deadline of this task as a {@code LocalDate}.
     * This method extracts the date part (without time) from the task's deadline {@code LocalDateTime}.
     *
     * @return the {@code LocalDate} representing the date of the deadline.
     */
    public LocalDate getFormattedDate() {
        return UtilParser.convertDateString(dateTimeStr);
    }

    /**
     * Gets the deadline for this task as String.
     * This method returns the deadline string that was passed during object construction.
     *
     * @return The String representing the deadline of the task.
     */
    public String getDateTimeStr() {
        return dateTimeStr;
    }

    /**
     * Converts the {@code LocalDateTime} to a formatted string representation.
     * This method uses a utility to convert the {@code LocalDateTime} to a string for display purposes.
     *
     * @param formattedDatetime The {@code LocalDateTime} to be converted to a string.
     * @return A formatted string representing the deadline {@code LocalDateTime}.
     */
    private String getFormattedDateTimeStr(LocalDateTime formattedDatetime) {
        return UtilParser.convertStringDateTimeFromFormatted(formattedDatetime);
    }

    /**
     * Returns a string representation of the deadline task, including its type, description,
     * and the deadline by which it must be completed.
     * The string is formatted as: "[D][status] description (by: deadline)".
     *
     * @return A formatted string that represents the deadline task, including the description and deadline.
     */
    @Override
    public String toString() {
        if (formattedDatetime == null) {
            return "[D]" + super.toString() + " (by: Invalid Deadline)";
        }
        String formattedDateTimeStr = getFormattedDateTimeStr(formattedDatetime);
        return "[D]" + super.toString() + " (by: " + formattedDateTimeStr + ")";
    }

}
