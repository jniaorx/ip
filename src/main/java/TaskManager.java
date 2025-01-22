import java.util.ArrayList;
import java.util.Scanner;
// to manage tasks that user inputs
public class TaskManager {
    private ArrayList<Task> tasksList = new ArrayList<>();

    /*
    public void handleTasks(Scanner scanner) {
        while (true) {
            // check for available input
            if (!scanner.hasNextLine()) {
                break;
            }

            String userInput = scanner.nextLine();
            // stop if user inputs bye
            if (userInput.equalsIgnoreCase("Bye")) {
                break;
            }

            // try here
            Commands.executeCommand(String userInput, this);
        }
        scanner.close();
    }
    */


    // method to add task to taskslist
    public void addTask(Task task) {
        // Task newTask = new Task(description);
        tasksList.add(task); // add new task to task list
        // System.out.println("    __________________");
        // System.out.println("    added: " + description);
        // System.out.println("    __________________");
    }

    // method to print taskslist
    public void printTaskList(ArrayList<Task> tasksList) {
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