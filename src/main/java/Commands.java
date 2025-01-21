import java.util.Scanner;
// different commands of chatbot
public class Commands {
    private final ChatAction greetings;
    private final ChatAction echo;

    // constructor
    public Commands(ChatAction greetings, ChatAction echo) {
        this.greetings = greetings;
        this.echo = echo;
    }

    // start chatting with user
    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        // start by greeting user
        greetings.execute(scanner);
        // echo user input
        echo.execute(scanner);
        // say goodbye when user inputs "bye"
        ((Greetings) greetings).sayBye();
        // close scanner
        scanner.close();
    }
}
