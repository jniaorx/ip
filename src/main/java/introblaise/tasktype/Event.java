package introblaise.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;

import introblaise.parsers.UtilParser;
import introblaise.task.Task;

/**
 * Represents a task with a specific duration that needs to be completed during a certain date or time.
 * This class extends the {@code Task} class and adds a from and to attribute to store the event duration.
 * of the task. It overrides the {@code toString()} method to provide a customized string representation.
 */
public class Event extends Task {
    protected LocalDateTime parsedFrom;
    protected LocalDateTime parsedTo;
    private final String from;
    private final String to;

    /**
     * Constructs an {@code introBlaise.task.Event} object with the specified description, start time, and end time.
     *
     * @param description A brief description of the event.
     * @param from The starting date/time of the event.
     * @param to The ending date/tine of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.parsedFrom = getParsedFormattedDateTime(from);
        this.parsedTo = getParsedFormattedDateTime(to);
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
     * This method extracts the date part (without time) from the task's from date time {@code LocalDateTime}.
     *
     * @return the {@code LocalDate} representing the date which event starts.
     */
    public LocalDate getFormattedFromDate() {
        String fromDate = extractStringDate(from);
        return UtilParser.convertDateString(fromDate);
    }

    private String extractStringDate(String from) {
        return UtilParser.extractStringDate(from);
    }
    /**
     * Gets the start date/tome of the event.
     *
     * @return The string start date/time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Gets the end date/time of the event.
     *
     * @return The string end date/time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Converts the {@code LocalDateTime} to a formatted string representation.
     * This method uses a utility to convert the {@code LocalDateTime} to a string for display purposes.
     *
     * @param parsedFrom The {@code LocalDateTime} to be converted to a string.
     * @return A formatted string representing the start of the event {@code LocalDateTime}.
     */
    private String getFormattedFromStr(LocalDateTime parsedFrom) {
        return UtilParser.convertStringDateTimeFromFormatted(parsedFrom);
    }

    /**
     * Converts the {@code LocalDateTime} to a formatted string representation.
     * This method uses a utility to convert the {@code LocalDateTime} to a string for display purposes.
     *
     * @param parsedTo The {@code LocalDateTime} to be converted to a string.
     * @return A formatted string representing the end of the event {@code LocalDateTime}.
     */
    private String getFormattedToStr(LocalDateTime parsedTo) {
        return UtilParser.convertStringDateTimeFromFormatted(parsedTo);
    }

    /**
     * Returns a string representation of the event, including its description,
     * start time, and end time.
     * The string is formatted as: "[E][status] description (from: start-time to: end-time)".
     *
     * @return A formatted string that represents the event.
     */
    @Override
    public String toString() {
        if (parsedFrom == null || parsedTo == null) {
            return "[E]" + super.toString() + " (by: Invalid Event Time)";
        }
        String formattedFromStr = getFormattedFromStr(parsedFrom);
        String formattedToStr = getFormattedToStr(parsedTo);
        return "[E]" + super.toString() + " (from: " + formattedFromStr + " to: " + formattedToStr + ")";
    }

}
