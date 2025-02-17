package introblaise.task;

/**
 * Represents a generic task with a description and completion status.
 * The {@code Task} class serves as a base class for different types of tasks.
 * It provides methods to manage the task's description and completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private String tag;
    private boolean isTagged;
    /**
     * Constructs a new introBlaise.task.Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
        this.isTagged = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
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
     * Returns whether the task is marked as done or not.
     *
     * @return False if the task is marked as undone, true if the task is marked as done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns whether the task is tagged or not.
     *
     * @return False if the task is untagged, true if the task is tagged.
     */
    public boolean getIsTagged() {
        return isTagged;
    }

    /**
     * Sets the tag for a task based on user input.
     *
     * @param label The tag description based on user input.
     */
    public void setTag(String label) {
        this.isTagged = true;
        this.tag = label.toLowerCase();
    }

    /**
     * Removes the tag of a task.
     */
    public void deleteTag() {
        this.isTagged = false;
        this.tag = "";
    }

    /**
     * Retrieves the tag of a task.
     *
     * @return The tag of a task.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's status icon and description if it is not tagged.
     * Returns the task's status icon, description and tag if it is tagged.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        if (!isTagged) {
            return "[" + getStatusIcon() + "] " + description;
        }
        return "[" + getStatusIcon() + "] " + "|" + tag + "| " + description;
    }
}
