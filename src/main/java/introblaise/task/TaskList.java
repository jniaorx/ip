package introblaise.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import introblaise.exceptions.EmptyDateException;
import introblaise.exceptions.EmptyTaskListException;
import introblaise.parsers.StorageTaskParser;
import introblaise.storage.Storage;
import introblaise.tasktype.Deadline;
import introblaise.tasktype.Event;

/**
 * Manages a list of tasks, allowing tasks to be added, removed, retrieved, and displayed.
 * The {@code TaskList} class serves as a central utility for handling user tasks,
 * providing operations to interact with and manipulate the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasksList;
    private final Storage storage;

    /**
     * Constructs a new {@code TaskList} for users to add tasks in it.
     *
     * @param storage The storage handler used to persist and retrieve tasks.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasksList = new ArrayList<>();
        loadTask();
    }

    /**
     * Loads tasks from storage and populates the task list.
     */
    public void loadTask() {
        tasksList.addAll(storage.loadTasksFromFile());
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
     * Returns a copy of the full list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasksList);
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
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasksList.add(task);
        saveTasks();
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
     * Saves the tasks list to storage.
     * Converts task objects to strings before saving them.
     */
    public void saveTasks() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task : tasksList) {
            String taskStr = StorageTaskParser.taskToString(task);
            taskStrings.add(taskStr);
        }
        storage.saveTasks(taskStrings);
    }

    /**
     * Prints the formatted task list.
     *
     * @return The formatted task list.
     */
    public String printTaskList() {
        if (isTaskListEmpty()) {
            return getEmptyTaskListMessage();
        } else {
            return formatTaskList();
        }
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    private boolean isTaskListEmpty() {
        return tasksList.isEmpty();
    }

    /**
     * Returns a message indicating that the task list is empty.
     *
     * @return A string message informing the user that there are no tasks.
     */
    private String getEmptyTaskListMessage() {
        try {
            throw new EmptyTaskListException("Oh no! Your task list is empty now. Please add tasks!");
        } catch (EmptyTaskListException e) {
            return e.getMessage();
        }
    }

    /**
     * Formates the task list into a numbered string representation.
     *
     * @return A formatted string displaying all tasks with their indices.
     */
    private String formatTaskList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            int indexNo = i + 1;
            Task currTask = getTask(i);
            result.append(indexNo).append(". ").append(currTask).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Returns a formatted list of tasks scheduled for a specific date.
     *
     * @param date The date for which tasks should be retrieved.
     * @return A formatted string containing tasks scheduled for the give date.
     */
    public String printTasksForDate(LocalDate date) throws EmptyDateException {
        StringBuilder result = new StringBuilder();
        for (Task task : tasksList) {
            if (taskIsScheduledForDate(task, date)) {
                result.append(task).append("\n");
            }
        }
        return result.toString().trim();
    }

    /**
     * Determines whether a task is scheduled for a given date.
     * @param task The task to check.
     * @param date The date to check against.
     * @return {@code true} if the task is scheduled for the specified date, {@code false} otherwise.
     */
    private boolean taskIsScheduledForDate(Task task, LocalDate date) throws EmptyDateException {
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            LocalDate deadlineDate = deadlineTask.getFormattedDate();
            return deadlineDate.equals(date);
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            LocalDate eventDate = eventTask.getFormattedFromDate();
            return eventDate.equals(date);
        }
        return false;
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
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
