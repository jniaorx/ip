package introblaise.task;

import introblaise.exceptions.EmptyTaskListException;
import introblaise.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, allowing tasks to be added, removed, retrieved, and displayed.
 * THe {@code TaskManager} class serves as a central utility for handling user tasks,
 * providing operations to interact with and manipulate the task list.
 */
public class TaskList {
    // Stores the list of tasks.
    private final ArrayList<Task> tasksList = new ArrayList<>();
    private final Storage storage; // Instance of saveData

    public TaskList() {
        this.storage = new Storage();
        List<String> loadedTasks = storage.loadTasks();

        // Convert loaded tasks from stings to introBlaise.task.Task objects and update taskslist
        for (String taskStr : loadedTasks) {
            Task task = stringToTask(taskStr);
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
        // introBlaise.task.Task newTask = new introBlaise.task.Task(description);
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
     * If the task list is empty, an {@code introBlaise.exceptions.EmptyTaskListException} is thrown and its
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
        storage.saveTasks(taskStrings); // Save tasks to file
    }

    public void printTasksForDate(LocalDate date) {
        for (Task task : tasksList) {
            // Check if the task is an instance of introBlaise.task.Deadline
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;

                // Get the date part from Deadline
                LocalDate taskDate = deadlineTask.getDeadline().toLocalDate();
                if (taskDate.isEqual(date)) {
                    System.out.println(task);
                }
            }
            // Check if the task is an instance of introBlaise.task.Event
            else if (task instanceof Event) {
                Event eventTask = (Event) task;
                LocalDate eventDate = eventTask.getFrom().toLocalDate();
                if (eventDate.isEqual(date)) {
                    System.out.println(task);
                }
            }
            // Skip ToDo tasks as they don't have a date
        }
    }

    public int getSize() {
        return tasksList.size();
    }


    private String taskToString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | "
                    + event.from + " to " + event.to;
        }
        return ""; // Default case
    }

    private Task stringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 4) { // Check if the parts array has enough data
                System.out.println("There is currently no tasks to be done.");
                return null;
            }

            String taskType = parts[0].trim();
            String isDone = parts[1].trim();
            String description = parts[2].trim();

            switch (taskType) {
            case "T":
                Task todo = new ToDo(description);
                if ("1".equals(isDone)) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String deadlineDate = parts[3].trim();
                Task deadline = new Deadline(description, deadlineDate);
                if ("1".equals(isDone)) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String eventDate = parts[3].trim();
                String[] eventDetails = eventDate.split(" to ");
                Task event = new Event(description, eventDetails[0], eventDetails[1]);
                if ("1".equals(isDone)) {
                    event.markAsDone();
                }
                return event;
            default:
                System.out.println("Skipping corrupted task line: " + line);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task line: " + e.getMessage());
            return null;
        }
    }
}