/**
 * Represents a task that occurs within a specific time frame.
 * The {@code Event} class extends the {@code Task} class to include
 * additional details about the start time and end time of the task.
 */
public class Event extends Task{
    /**
     * The starting date/time of the event.
     */
    protected String from;

    /**
     * The ending date/time of the event.
     */
    protected String to;

    /**
     * Constructs an {@code Event} object with the specified description, start time, and end time.
     * @param description A brief description of the event.
     * @param from The starting date/time of the event.
     * @param to The ending date/tine of the event.
     */
    public Event(String description, String from, String to) {
        super(description); // Calls the constructor of the Task class
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event, including its description,
     * start time, and end time.
     * @return A formatted string that represents the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
