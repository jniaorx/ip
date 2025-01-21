import java.util.Scanner;
// different commands of chatbot
public class Commands {
    private final ChatAction greetings;
    private final TaskManager taskManager;

    // constructor
    public Commands(ChatAction greetings, TaskManager taskManager) {
        this.greetings = greetings;
        this.taskManager = taskManager;
    }

    // start chatting with user
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        // start by greeting user
        greetings.execute(scanner);
        // manage task list
        taskManager.handleTasks();
        // say goodbye when user inputs "bye"
        ((Greetings) greetings).sayBye();
        // close scanner
        scanner.close();
    }
}
