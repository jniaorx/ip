package introblaise.ui;

/**
 * Handles interactions with the user, including displaying messages and reading input.
 */
public class Ui {
    /**
     * Displays the bot's welcome message with an ASCII logo and greets the user.
     */
    public String showWelcome() {
        String logo =
                """
                         __  .__    __. .___________..______         ______          \s
                        |  | |  \\ |  | |           ||   _  \\      /  __  \\        \s
                        |  | |   \\|  | `---|  |----`|  |_)  |     |  |  |  |        \s
                        |  | |  . `   |     |  |     |      /      |  |  |  |        \s
                        |  | |  |\\   |     |  |     |  |\\  \\----|  `--'  |        \s
                        |__| |__| \\__|     |__|     | _| `._____| \\______/         \s
                                                                                     \s
                        .______     __          ___       __        _______. _______  \s
                        |   _  \\  |  |        /   \\     |  |     /       ||   ____|\s
                        |  |_)  |  |  |       /  ^  \\    |  |    |   (----`|  |__   \s
                        |   _  <   |  |      /  /_\\  \\  |  |     \\   \\  |   __|  \s
                        |  |_)  |  |  `----./  _____   \\ |  | .----)   |   |  |____ \s
                        |______/   |_______/__/     \\__\\|__| |_______/    |_______|\s
                        """;

        return """
                WASSUP!
                I am INTROBLAISE,
                Your FAVOURITE task manager!
                Please type your commands to start :)
                Type "help" to show user guide!""";
    }
}
