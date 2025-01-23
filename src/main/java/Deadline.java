/**
 * Represents a task with a specific deadline that needs to be completed before a certain date or time.
 * This class extends the {@code Task} class and adds a deadline attribute to store the due date/time
 * of the task. It overrides the {@code toString()} method to provide a customized string representation.
 */
public class Deadline extends Task{
    /** The deadline by which the task should be completed, represented as a string. */
    protected String by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * @param description The description of the task.
     * @param by The deadline (date/time) by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its type, description,
     * and the deadline by which it must be completed.
     * @return A string in the format "[D][status] description (by: deadline)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
