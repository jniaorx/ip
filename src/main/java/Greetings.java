import java.util.Scanner;
// to greet users
public class Greetings implements ChatAction {
    @Override
    public void execute(Scanner scanner) {
        // greet user
        System.out.println("__________________");
        System.out.println("Sup! I am IntroBlaise.");
        System.out.println("What can I do for you?");
        System.out.println("__________________");
    }

    public void sayBye() {
        // if user input is "bye", say bye to user
        System.out.println("__________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("__________________");
    }
}
