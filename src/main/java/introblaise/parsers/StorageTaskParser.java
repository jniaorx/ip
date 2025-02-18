package introblaise.parsers;

import introblaise.task.Task;
import introblaise.tasktype.Deadline;
import introblaise.tasktype.Event;
import introblaise.tasktype.ToDo;

/**
 * The {@code StorageTaskParser} class provides utility methods for converting
 * {@link Task} objects to their string representations for storage and vice-versa.
 * It handles the specific format used for saving tasks to a file and loading them
 * back into the application.
 */
public class StorageTaskParser {

    /**
     * Converts a {@link Task} object to its string representation for storage.
     * The string format depends on the type of the task (ToDo, Deadline, or Event).
     * The format includes the task type, completion status, description, and optionally,
     * the tag label.
     *
     * @param task The {@link Task} object to convert.
     * @return The string representation of the task.
     */
    public static String taskToString(Task task) {
        boolean isDone = task.getIsDone();
        boolean isTagged = task.getIsTagged();
        String isDoneStr = getBooleanAsString(isDone);
        String isTaggedStr = getBooleanAsString(isTagged);
        String taskDescription = task.getDescription();
        String tagLabel = task.getTag();

        String base = getTaskBaseString(task, taskDescription, isDoneStr, isTaggedStr, tagLabel);

        return base;
    }

    /**
     * Converts a boolean value to its string representation ("1" for true, "0" for false).
     *
     * @param value The boolean value.
     * @return "1" if the value is true, "0" if the value is false.
     */
    private static String getBooleanAsString(boolean value) {
        return value ? "1" : "0";
    }

    /**
     * Generates the base string representation of a task, excluding the tag information.
     * This string includes the task type, completion status, and description.
     *
     * @param task        The {@link Task} object.
     * @param description The task description.
     * @param isDoneStr   The string representation of the completion status ("1" or "0").
     * @return The base string representation of the task.
     */
    private static String getTaskBaseString(Task task, String description, String isDoneStr, String isTaggedStr,
                                            String taglabel) {
        if (task instanceof ToDo) {
            return buildToDoSring(description, isDoneStr, isTaggedStr, taglabel);
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return buildDeadlineString(description, isDoneStr, deadlineTask.getDateTimeStr(), isTaggedStr, taglabel);
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return buildEventString(description, isDoneStr, eventTask.getFrom(), eventTask.getTo(),
                    isTaggedStr, taglabel);
        }
        return null;
    }

    /**
     * Builds the string representation of a {@link ToDo} task.
     *
     * @param description The task description.
     * @param isDoneStr   The string representation of the completion status.
     * @return The string representation of the ToDo task.
     */
    private static String buildToDoSring(String description, String isDoneStr, String isTaggedStr, String tagLabel) {
        return String.format("T | %s | %s | %s | %s", isDoneStr, description, isTaggedStr, tagLabel);
    }

    /**
     * Builds the string representation of a {@link Deadline} task.
     *
     * @param description The task description.
     * @param isDoneStr   The string representation of the completion status.
     * @param dateTimeStr The string representation of the deadline date and time.
     * @return The string representation of the Deadline task.
     */
    private static String buildDeadlineString(String description, String isDoneStr, String dateTimeStr,
                                              String isTaggedStr, String tagLabel) {
        return String.format("D | %s | %s | %s | %s | %s", isDoneStr, description, dateTimeStr, isTaggedStr, tagLabel);
    }

    /**
     * Builds the string representation of an {@link Event} task.
     *
     * @param description The task description.
     * @param isDoneStr   The string representation of the completion status.
     * @param fromStr     The string representation of the event start date and time.
     * @param toStr       The string representation of the event end date and time.
     * @return The string representation of the Event task.
     */
    private static String buildEventString(String description, String isDoneStr, String fromStr, String toStr,
                                           String isTaggedStr, String tagLabel) {
        return String.format("E | %s | %s | %s to %s | %s | %s", isDoneStr, description, fromStr, toStr, isTaggedStr,
                tagLabel);
    }

    /**
     * Converts a string representation of a task (loaded from storage) into a {@link Task} object.
     * This method parses the string and creates the appropriate {@link Task} subclass
     * (ToDo, Deadline, or Event).
     *
     * @param line The string representing a task, as loaded from storage.
     * @return The {@link Task} object represented by the string, or {@code null} if the
     *         string cannot be parsed or represents an unknown task type.
     */
    public static Task stringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) { // Adjust length check
                System.out.println("There is currently no tasks to be done.");
                return null;
            }
            Task task = createTaskFromParts(parts);
            if (task != null) {
                applyTaskMetaData(task, parts);
            }
            return task;

        } catch (Exception e) {
            System.out.println("Error parsing task line: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a {@link Task} object based on the parsed parts of the task string.
     * This method determines the task type and creates the corresponding {@link Task}
     * subclass (ToDo, Deadline, or Event).
     *
     * @param parts The array of strings representing the parts of the task string.
     * @return The created {@link Task} object, or {@code null} if the task type is unknown.
     */
    private static Task createTaskFromParts(String[] parts) {
        String taskType = parts[0].trim();
        String taskDescription = parts[2].trim();

        switch (taskType) {
        case "T":
            return new ToDo(taskDescription);
        case "D":
            String deadlineDate = parts[3].trim();
            return new Deadline(taskDescription, deadlineDate);
        case "E":
            String eventDate = parts[3].trim();
            String[] eventDetails = eventDate.split(" to ");
            return new Event(taskDescription, eventDetails[0], eventDetails[1]);
        default:
            return null;
        }
    }

    /**
     * Applies the task metadata (completion status and tag) to the created {@link Task} object.
     *
     * @param task  The {@link Task} object to apply the metadata to.
     * @param parts The array of strings representing the parts of the task string.
     */
    public static void applyTaskMetaData(Task task, String[] parts) {
        String isDone = parts[1].trim();
        String isTagged = parts[parts.length - 2].trim();
        String label = parts[parts.length - 1].trim();

        if ("1".equals(isDone)) {
            task.markAsDone();
        }
        if ("1".equals(isTagged)) {
            task.setTag(label);
        }
    }
}
