import java.util.ArrayList;
import java.util.Scanner;
// to manage tasks that user inputs
public class TaskManager {
    private ArrayList<Task> tasksList = new ArrayList<>();

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
                System.out.println("        " + currTask);
                System.out.println("    __________________");

            // unmark task that has not been done
            } else if (userInput.startsWith("unmark")) {
                int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
                Task currTask = tasksList.get(taskNo);
                currTask.markAsUndone();

                System.out.println("    __________________");
                System.out.println("    OK, I've marked is task as not done yet:");
                System.out.println("    " + currTask);
                System.out.println("    __________________");

            // add todo task into tasklist
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring((5));
                ToDo todoTask = new ToDo(description);
                addTask(todoTask);
                int numOfTask = tasksList.size();

                System.out.println("    __________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + todoTask);
                System.out.println("    Now you have " + numOfTask + " tasks in the list." );
                System.out.println("    __________________");

            // add deadline task to tasklist
            } else if (userInput.startsWith("deadline")) {
                String description = userInput.substring(9, userInput.indexOf("/"));
                int separator = userInput.indexOf("/") + 4;
                String deadline = userInput.substring(separator);

                Deadline deadlineTask = new Deadline(description, deadline);
                addTask(deadlineTask);
                int numOfTask = tasksList.size(); // no of task in task list

                System.out.println("    __________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + deadlineTask);
                System.out.println("    Now you have " + numOfTask + " tasks in the list.");
                System.out.println("    __________________");

            // add event task into tasklist
            } else if (userInput.startsWith("event")) {
                String description = userInput.substring(6, userInput.indexOf("/"));
                // split description into segments
                String[] segments = userInput.substring(6).split("/");
                String from = segments[1].substring(5);
                String to = segments[2].substring(3);

                Event eventTask = new Event(description, from, to);
                addTask(eventTask);
                int numOfTask = tasksList.size(); // no of task in task list

                System.out.println("    __________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + eventTask);
                System.out.println("    Now you have " + numOfTask + " tasks in the list.");
                System.out.println("    __________________");

            } else {
                System.out.println("Invalid Task");
            }
        }
        scanner.close();
    }

    // method to add task to task list
    public void addTask(Task task) {
        // Task newTask = new Task(description);
        tasksList.add(task); // add new task to task list
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
                System.out.println("    " + (i + 1) + ". " + tasksList.get(i));
            }
            System.out.println("    __________________");
        }
    }
}
