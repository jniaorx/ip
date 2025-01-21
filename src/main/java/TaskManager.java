import java.util.ArrayList;
import java.util.Scanner;
// to manage tasks that user inputs
public class TaskManager {
    private ArrayList<String> tasksList = new ArrayList<>();

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
            // add task to task list
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    // method to add task to task list
    public void addTask(String task) {
        tasksList.add(task);
        System.out.println("    __________________");
        System.out.println("    added: " + task);
        System.out.println("    __________________");
    }

    // method to print out task list
    public void printTaskList() {
        if (tasksList.isEmpty()) {
            System.out.println("Your task list is empty now! Please add tasks!");
        } else {
            System.out.println("    __________________");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasksList.get(i));
            }
            System.out.println("    __________________");
        }
    }
}
