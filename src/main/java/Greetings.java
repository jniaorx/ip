import java.util.Scanner;
public class Greetings {
    public void greetUser() {
        Scanner scanner = new Scanner(System.in);

        // greet user
        System.out.println("__________________");
        System.out.println("Sup! I am IntroBlaise.");
        System.out.println("What can I do for you?");
        System.out.println("__________________");

        // wait for user input
        String userInput = scanner.nextLine();

        // while userInput is not "bye", repeat user input
        while(!userInput.equals("bye")) {
            // repeat user input
            System.out.println(userInput);
            System.out.println("__________________");

            // wait for user input
            userInput = scanner.nextLine();
        }

        // if user inpyt is "bye", say bye to user
        System.out.println("__________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("__________________");

        // close scanner
        scanner.close();
    }
}
