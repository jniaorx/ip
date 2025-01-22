import java.util.Scanner;
// different commands of chatbot
public class Commands {
    private final Greetings greetings;
    private final TaskManager taskManager;

    // constructor
    public Commands(Greetings greetings, TaskManager taskManager) {
        this.greetings = greetings;
        this.taskManager = taskManager;
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        greetings.greet(scanner);
        taskManager.handleTasks(scanner);
        greetings.sayBye();
    }
}
