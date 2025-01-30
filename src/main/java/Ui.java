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
    public void showWelcome() {
        String logo =
                ".___        __               __________.__         .__\n" +
                        "|   | _____/  |________  ____\\______   \\  | _____  |__| ______ ____\n" +
                        "|   |/    \\   __\\_  __ \\/  _ \\|    |  _/  | \\__  \\ |  |/  ___// __ \\\n" +
                        "|   |   |  \\  |  |  | \\(  <_> )    |   \\  |__/ __ \\|  |\\___ \\\\  ___/\n" +
                        "|___|___|  /__|  |__|   \\____/|______  /____(____  /__/____  >\\___  >\n" +
                        "\n";
        System.out.println("Hello from\n" + logo);

        // Delegate greeting responsibility to Greetings class
        greetings.greet(scanner);
    }

    /**
     * Reads user input from the console.
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
