package introblaise.task;

/**
 * Represents a task without any date/time attached to it.
 * This class extends the {@code introBlaise.task.Task} class and add the task without any date/time attached
 * to the list. It overrides the {@code toString()} method to provide a customized string representation.
 */
public class ToDo extends Task {
    /**
     * Constructs a new {@code introBlaise.task.ToDo} task with the specified description.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its type and description.
     * @return A string in the format "[T][status] description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
