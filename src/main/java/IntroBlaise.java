import java.util.Scanner;

public class IntroBlaise {
    public static void main(String[] args) throws IntroBlaiseException {
        String logo =
                ".___        __               __________.__         .__\n" +
                        "|   | _____/  |________  ____\\______   \\  | _____  |__| ______ ____\n" +
                        "|   |/    \\   __\\_  __ \\/  _ \\|    |  _/  | \\__  \\ |  |/  ___// __ \\\n" +
                        "|   |   |  \\  |  |  | \\(  <_> )    |   \\  |__/ __ \\|  |\\___ \\\\  ___/\n" +
                        "|___|___|  /__|  |__|   \\____/|______  /____(____  /__/____  >\\___  >\n" +
                        "\n";

        System.out.println("Hello from\n" + logo);

        TaskManager taskManager = new TaskManager();
        Commands commands = new Commands(taskManager);
        Greetings greetings = new Greetings();

        Scanner scanner = new Scanner(System.in);
        greetings.greet(scanner); // say hi to user
        while(true) {
            String userInput = scanner.nextLine();
            // say bye to user if user inputs "bye"
            if (userInput.equalsIgnoreCase("Bye")) {
                greetings.sayBye();
                break;
            }
            // if not, execute whatever commands that user inputs
            commands.executeCommand(userInput);
        }
        scanner.close();
    }
}
