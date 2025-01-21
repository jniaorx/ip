import java.util.ArrayList;
import java.util.Scanner;
// to manage tasks that user inputs
public class TaskManager {
    private ArrayList<Task> tasksList = new ArrayList<>();

    public void handleTasks() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            // stop if user inputs bye
            if (userInput.equalsIgnoreCase("Bye")) {
                break;
            // print task list if user inputs list
            } else if (userInput.equalsIgnoreCase("List")) {
                printTaskList();
            } else if (userInput.startsWith("mark")) {
                int taskNo = Integer.parseInt(userInput.substring(5)) - 1;
                Task currTask = tasksList.get(taskNo);
                currTask.markAsDone();
                System.out.println("    __________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    [" + currTask.getStatusIcon() + "] " + currTask.description);
                System.out.println("    __________________");
            } else if (userInput.startsWith("unmark")) {
                int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
                Task currTask = tasksList.get(taskNo);
                currTask.markAsUndone();
                System.out.println("    __________________");
                System.out.println("    OK, I've marked is task as not done yet:");
                System.out.println("    [" + currTask.getStatusIcon() + "] " + currTask.description);
                System.out.println("    __________________");
            // add task to task list
            } else {
                addTask(userInput);
            }
        }
        scanner.close();
    }

    // method to add task to task list
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasksList.add(newTask);
        System.out.println("    __________________");
        System.out.println("    added: " + description);
        System.out.println("    __________________");
    }

    // method to print out task list
    public void printTaskList() {
        if (tasksList.isEmpty()) {
            System.out.println("Your task list is empty now! Please add tasks!");
        } else {
            System.out.println("    __________________");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + "[" + tasksList.get(i).getStatusIcon() + "] " + tasksList.get(i).description);
            }
            System.out.println("    __________________");
        }
    }
}
