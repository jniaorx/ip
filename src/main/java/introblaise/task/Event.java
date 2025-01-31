package introblaise.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task that occurs within a specific time frame.
 * The {@code introBlaise.task.Event} class extends the {@code introBlaise.task.Task} class to include
 * additional details about the start time and end time of the task.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * The starting date/time of the event.
     */
    protected LocalDateTime parsedFrom;

    /**
     * The ending date/time of the event.
     */
    protected LocalDateTime parsedTo;

    /**
     * Constructs an {@code introBlaise.task.Event} object with the specified description, start time, and end time.
     * @param description A brief description of the event.
     * @param from The starting date/time of the event.
     * @param to The ending date/tine of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.parsedFrom = parseDateTime(from);
        this.parsedTo = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    public LocalDateTime getFrom() {
        return parsedFrom;
    }

    public LocalDateTime getTo() {
        return parsedTo;
    }

    /**
     * Returns a string representation of the event, including its description,
     * start time, and end time.
     * @return A formatted string that represents the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
        String formattedFrom = parsedFrom.format(formatter);
        String formattedTo = parsedTo.format(formatter);

        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

}
