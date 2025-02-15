package introblaise.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import introblaise.exceptions.EmptyTaskListException;
import introblaise.storage.Storage;
import introblaise.tasktype.Deadline;
import introblaise.tasktype.Event;
import introblaise.tasktype.ToDo;

/**
 * Manages a list of tasks, allowing tasks to be added, removed, retrieved, and displayed.
 * THe {@code TaskManager} class serves as a central utility for handling user tasks,
 * providing operations to interact with and manipulate the task list.
 */
public class TaskList {
    // Stores the list of tasks.
    private final ArrayList<Task> tasksList = new ArrayList<>();
    private final Storage storage; // Instance of saveData
    /**
     * Constructs a new task list for users to add tasks in it.
     */
    public TaskList() {
        this.storage = new Storage();
        List<String> loadedTasks = storage.loadTasksFromFile();

        // Convert loaded tasks from strings to Task objects and update taskslist
        for (String taskStr : loadedTasks) {
            Task task = stringToTask(taskStr);
            if (task != null) {
                tasksList.add(task);
            }
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        // introBlaise.task.Task newTask = new introBlaise.task.Task(description);
        tasksList.add(task); // Add new task to the list.
        // System.out.println("    __________________");
        // System.out.println("    added: " + description);
        // System.out.println("    __________________");
        saveTasks(); // Save updated task list to storage.
    }

    /**
     * Removes a specific task from the task list.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        tasksList.remove(task);
        saveTasks();
    }

    /**
     * Prints the list of tasks to the console.
     * If the task list is empty, an {@code introBlaise.exceptions.EmptyTaskListException} is thrown and its
     * message is printed instead.
     */
    public String printTaskList() {
        try {
            // Check if the task list is empty.
            if (tasksList.isEmpty()) {
                throw new EmptyTaskListException("Oh no! Your task list is empty now. Please add tasks!");
            } else {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < tasksList.size(); i++) {
                    result.append(i + 1).append(". ").append(getTask(i)).append("\n");
                }
                return result.toString().trim();
            }
        } catch (EmptyTaskListException e) {
            // Handle empty task list by printing the exception message.
            return e.getMessage();
        }
    }

    /**
     * Retrieves a specific task from the task list.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int taskIndex) {
        return tasksList.get(taskIndex);
    }

    /**
     * Returns the full list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Saves the tasks list to storage.
     * Converts task objects to strings before saving them.
     */
    public void saveTasks() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task : tasksList) {
            taskStrings.add(taskToString(task)); // Convert tasks to strings
        }
        storage.saveTasks(taskStrings); // Save tasks to file
    }

    /**
     * Prints tasks scheduled for a specific date.
     *
     * @param date The date to check for tasks.
     */
    public String printTasksForDate(LocalDate date) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasksList) {
            // Check if the task is an instance of introBlaise.task.Deadline
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;

                // Get the date part from Deadline
                LocalDate taskDate = deadlineTask.getDeadline().toLocalDate();
                if (taskDate.isEqual(date)) {
                    result.append(task).append("\n");
                }
            } else if (task instanceof Event) { // Check if the task is an instance of task.Event
                Event eventTask = (Event) task;
                LocalDate eventDate = eventTask.getParsedFrom().toLocalDate();
                if (eventDate.isEqual(date)) {
                    result.append(task).append("\n");
                }
            }
            // Skip ToDo tasks as they don't have a specific date
        }
        return result.toString().trim();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasksList.size();
    }

    /**
     * Converts a task object into a string representation.
     *
     * @param task The task to be converted.
     * @return A string representation of the task.
     */
    private String taskToString(Task task) {
        String base = "";
        if (task instanceof ToDo) {
            base = "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            base = "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.description + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            base = "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.description + " | " + event.getFrom()
                    + " to " + event.getTo();
        }
        return base + " | " + (task.getIsTagged() ? "1" : "0") + " | " + task.getTag(); // Add tag info
    }

    /**
     * Converts a string representation of a task into a task object.
     *
     * @param line The string representing a task.
     * @return The task object represented by the string.
     */
    private Task stringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) { // Adjust length check
                System.out.println("There is currently no tasks to be done.");
                return null;
            }

            String taskType = parts[0].trim();
            String isDone = parts[1].trim();
            String description = parts[2].trim();
            String isTagged = "0"; // Default, in case it's not present
            String tag = "";

            if (parts.length > 3) { // Check if tag info is present
                isTagged = parts[parts.length - 2].trim();
                tag = parts[parts.length - 1].trim();
            }

            Task task = null; // Declare task variable here
            switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String deadlineDate = parts[3].trim();
                task = new Deadline(description, deadlineDate);
                break;
            case "E":
                String eventDate = parts[3].trim();
                String[] eventDetails = eventDate.split(" to ");
                task = new Event(description, eventDetails[0], eventDetails[1]);
                break;
            default:
                System.out.println("Skipping corrupted task line: " + line);
                return null;
            }

            if (task != null) {
                if ("1".equals(isDone)) {
                    task.markAsDone();
                }
                if ("1".equals(isTagged)) {
                    task.setTag(tag);
                }
            }
            return task;

        } catch (Exception e) {
            System.out.println("Error parsing task line: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finds and lists tasks that match the keyword based on user input.
     *
     * @param keyword The keyword of task that user wants to find.
     * @return A list of tasks that matches the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
