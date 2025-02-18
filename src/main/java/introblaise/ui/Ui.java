package introblaise.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user, including displaying messages and reading input.
 */
public class Ui {
    private Scanner scanner;
    private Greetings greetings;

    /**
     * Constructor initializes the scanner for user input and the Greetings component.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.greetings = new Greetings();
    }

    /**
     * Displays the bot's welcome message with an ASCII logo and greets the user.
     */
    public String showWelcome() {
        String logo =
                        " __  .__    __. .___________..______         ______           \n"
                        + "|  | |  \\ |  | |           ||   _  \\      /  __  \\         \n"
                        + "|  | |   \\|  | `---|  |----`|  |_)  |     |  |  |  |         \n"
                        + "|  | |  . `   |     |  |     |      /      |  |  |  |         \n"
                        + "|  | |  |\\   |     |  |     |  |\\  \\----|  `--'  |         \n"
                        + "|__| |__| \\__|     |__|     | _| `._____| \\______/          \n"
                        + "                                                              \n"
                        + ".______     __          ___       __        _______. _______   \n"
                        + "|   _  \\  |  |        /   \\     |  |     /       ||   ____| \n"
                        + "|  |_)  |  |  |       /  ^  \\    |  |    |   (----`|  |__    \n"
                        + "|   _  <   |  |      /  /_\\  \\  |  |     \\   \\  |   __|   \n"
                        + "|  |_)  |  |  `----./  _____   \\ |  | .----)   |   |  |____  \n"
                        + "|______/   |_______/__/     \\__\\|__| |_______/    |_______| \n";

        return "WASSUP!\n" + "I am INTROBLAISE,\n" + "Your FAVOURITE task manager!\n"
                + "Please type your commands to start :)\n";
    }

    /**
     * Reads user input from the console.
     *
     * @return The input string entered by the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a farewell message when the user exits.
     */
    public void showGoodbye() {
        greetings.sayBye();
    }

    /**
     * Closes the scanner when the application is shutting down.
     */
    public void closeScanner() {
        scanner.close();
    }
}
