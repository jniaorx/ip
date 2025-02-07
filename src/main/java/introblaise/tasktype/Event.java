package introblaise.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import introblaise.task.Task;

/**
 * Represents a task that occurs within a specific time frame.
 * The {@code introBlaise.task.Event} class extends the {@code introBlaise.task.Task} class to include
 * additional details about the start time and end time of the task.
 */
public class Event extends Task {
    /** The parsed start date/time of the event as a LocalDateTime object. */
    protected LocalDateTime parsedFrom;

    /** The parsed end date/time of the event as a LocalDateTime object. */
    protected LocalDateTime parsedTo;
    /** The start date/time of the event represented as a string. */
    private String from;

    /** The end date/time of the event represented as a string. */
    private String to;

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
        this.parsedFrom = parseEventDateTime(from);
        this.parsedTo = parseEventDateTime(to);
    }

    /**
     * Parses a string representation of date/time into a LocalDateTime object.
     * The expected format for the date/time string is "d-MM-yyyy HHmm".
     *
     * @param dateTimeStr The string representation of date/time.
     * @return A LocalDateTime object parsed from the provided string.
     */
    private LocalDateTime parseEventDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use 'd-MM-yyyy HHmm'.");
        }
        return null;
    }

    /**
     * Gets the start date/time of the event.
     *
     * @return The parsed start date/time as a LocalDateTime object.
     */
    public LocalDateTime getParsedFrom() {
        return parsedFrom;
    }

    /**
     * Gets the end date/time of the event.
     *
     * @return The parsed end date/time as a LocalDateTime object.
     */
    public LocalDateTime getParsedTo() {
        return parsedTo;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedFrom = parsedFrom.format(formatter);
        String formattedTo = parsedTo.format(formatter);

        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

}
