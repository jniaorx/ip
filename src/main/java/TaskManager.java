import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, allowing tasks to be added, removed, retrieved, and displayed.
 * THe {@code TaskManager} class serves as a central utility for handling user tasks,
 * providing operations to interact with and manipulate the task list.
 */
public class TaskManager {
    // Stores the list of tasks.
    private final ArrayList<Task> tasksList = new ArrayList<>();
    private final SaveData saveData; // Instance of saveData

    public TaskManager() {
        this.saveData = new SaveData();
        List<String> loadedTasks = saveData.loadTasks();

        // Convert loaded tasks from stings to Task objects and update taskslist
        for (String taskStr : loadedTasks) {
            Task task = saveData.stringToTask(taskStr);
            if (task != null) {
                tasksList.add(task);
            }
        }

    }

    /**
     * Adds a new task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        // Task newTask = new Task(description);
        tasksList.add(task); // Add new task to the list.
        // System.out.println("    __________________");
        // System.out.println("    added: " + description);
        // System.out.println("    __________________");
        saveTasks();
    }

    /**
     * Removes a specific task from the task list.
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {

        tasksList.remove(task);
        saveTasks();;

    }

    /**
     * Prints the list of tasks to the console.
     * If the task list is empty, an {@code EmptyTaskListException} is thrown and its
     * message is printed instead.
     */
    public void printTaskList() {
        try {
            // Check if the task list is empty.
            if (tasksList.isEmpty()) {
                throw new EmptyTaskListException("Oh no! Your task list is empty now. Please add tasks!");
            } else {
                // Print all tasks in the list.
                System.out.println("    _________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + getTask(i));
                }
                System.out.println("    _________________________________");
            }
        } catch (EmptyTaskListException e) {
            // Handle empty task list by printing the exception message.
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves a specific task from the task list.
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int taskIndex) {

        return tasksList.get(taskIndex);
    }

    /**
     * Returns the full list of tasks.
     * @return AN {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasksList() {

        return tasksList;
    }

    public void saveTasks() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task : tasksList) {
            taskStrings.add(taskToString(task)); // Convert tasks to strings
        }
        saveData.saveTasks(taskStrings); // Save tasks to file
    }

    private String taskToString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + event.from + " to " + event.to;
        }
        return ""; // Default case
    }
}