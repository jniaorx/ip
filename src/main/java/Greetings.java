import java.util.Scanner;

public class Greetings {
    public Greetings() {
    }

    public void greet(Scanner scanner) {  // pass scanner as parameter
        // greet user
        System.out.println("    _________________________________");
        System.out.println("    Sup! I am IntroBlaise. What's your name?");
        if (scanner.hasNextLine()) {      // check for available input
            String name = scanner.nextLine();
            System.out.println("    _________________________________");
            System.out.println("    Hi " + name + "!(ʘ‿ʘ)╯ Nice to meet you! You can start adding tasks to your very own task list! :)");
            System.out.println("    _________________________________");
        }
    }

    public void sayBye() {
        // if user input is "bye", say bye to user
        System.out.println("    _________________________________");
        System.out.println("    Bye! Hope to see you again soon! :(");
        System.out.println("    _________________________________");
    }
}