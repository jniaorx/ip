/**
 * Represents a generic task with a description and completion status.
 * The {@code Task} class serves as a base class for different types of tasks.
 * It provides methods to manage the task's description and completion status.
 */
public class Task {
    // The description of the task.
    protected String description;

    // Indicates whether the task is marked as done.
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Default status is not done.
    }

    /**
     * Returns the status icon representing whether the task is done.
     * @return "X" if the task is done, otherwise a space (" ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with "X".
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's status icon and description
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
