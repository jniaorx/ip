package introblaise.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import introblaise.task.Task;

/**
 * Represents a task with a specific deadline that needs to be completed before a certain date or time.
 * This class extends the {@code introBlaise.task.Task} class and adds a deadline attribute to store the due date/time
 * of the task. It overrides the {@code toString()} method to provide a customized string representation.
 */
public class Deadline extends Task {
    /** The deadline by which the task should be completed, represented as a string. */
    private String by;

    /** The actual deadline represented as a LocalDateTime object. */
    private LocalDateTime deadline;

    /**
     * Constructs a new {@code introBlaise.task.Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline (date/time) by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = parseDeadline(by);
    }

    /**
     * Parses the deadline string into a LocalDateTime object.
     * The expected format for the deadline string is "d-MM-yyyy HHmm".
     *
     * @param by The deadline string.
     * @return A LocalDateTime object representing the deadline.
     */
    private LocalDateTime parseDeadline(String by) throws IllegalArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
            return LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use 'd-MM-yyyy HHmm'.");
        }
        return null;
    }

    /**
     * Gets the deadline for this task as a LocalDateTime object.
     *
     * @return The LocalDateTime representing the deadline of the task.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Gets the deadline for this task as String.
     *
     * @return The String representing the deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task, including its type, description,
     * and the deadline by which it must be completed.
     *
     * @return A string in the format "[D][status] description (by: deadline)"
     */
    @Override
    public String toString() {
        if (deadline == null) {
            return "[D]" + super.toString() + " (by: Invalid Deadline)";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDate = deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

}
