import java.util.Scanner;
// echos user input
public class Echo implements ChatAction{
    public void execute(Scanner scanner) {
        // wait for user input
        String userInput = scanner.nextLine();
        // while userInput is not "bye", repeat user input
        while (!userInput.equals("bye")) {
            // repeat user input
            System.out.println(userInput);
            System.out.println("__________________");

            // wait for user input
            userInput = scanner.nextLine();
        }
    }
}
