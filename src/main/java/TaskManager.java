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
            // mark task that has been done
            } else if (userInput.startsWith("mark")) {
                int taskNo = Integer.parseInt(userInput.substring(5)) - 1;
                Task currTask = tasksList.get(taskNo);
                currTask.markAsDone();
                System.out.println("    __________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + currTask.toString());
                System.out.println("    __________________");
            // unmark task that has not been done
            } else if (userInput.startsWith("unmark")) {
                int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
                Task currTask = tasksList.get(taskNo);
                currTask.markAsUndone();
                System.out.println("    __________________");
                System.out.println("    OK, I've marked is task as not done yet:");
                System.out.println("    " + currTask.toString());
                System.out.println("    __________________");
            // add task with deadline to task list
            } else if (userInput.startsWith("deadline")) {
                String description = userInput.substring(9, userInput.indexOf("/"));
                int separator = userInput.indexOf("/") + 4;
                String deadline = userInput.substring(separator);

                Deadline deadlineTask = new Deadline(description, deadline);
                addTask(deadlineTask.toString());
                int numOfTask = tasksList.size(); // no of task in task list

                System.out.println("    __________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + deadlineTask.toString());
                System.out.println("    Now you have " + numOfTask + " tasks in the list." );
                System.out.println("    __________________");
            } else {
                addTask(userInput);
            }
        }
        scanner.close();
    }

    // method to add task to task list
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasksList.add(newTask); // add new task to task list
        /*
        System.out.println("    __________________");
        System.out.println("    added: " + description);
        System.out.println("    __________________");
        */
    }

    // method to print out task list
    public void printTaskList() {
        if (tasksList.isEmpty()) {
            System.out.println("Your task list is empty now! Please add tasks!");
        } else {
            System.out.println("    __________________");
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasksList.get(i).description);
            }
            System.out.println("    __________________");
        }
    }
}
