import java.util.ArrayList;
// to manage tasks that user inputs
public class TaskManager {
    private final ArrayList<Task> tasksList = new ArrayList<>();

    // method to add task to taskslist
    public void addTask(Task task) {
        // Task newTask = new Task(description);
        tasksList.add(task); // add new task to task list
        // System.out.println("    __________________");
        // System.out.println("    added: " + description);
        // System.out.println("    __________________");
    }

    public void removeTask(Task task) {
        tasksList.remove(task);
    }

    // method to print taskslist
    public void printTaskList() {
        if (tasksList.isEmpty()) {
            System.out.println("Your task list is empty now! Please add tasks!");
        } else {
            System.out.println("    __________________");
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + getTask(i));
            }
            System.out.println("    __________________");
        }
    }

    // method to get a specific task from taskslist
    public Task getTask(int taskIndex) {
        return tasksList.get(taskIndex);
    }

    // method to get task list
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }
}