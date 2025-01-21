public class IntroBlaise {
    public static void main(String[] args) {
        String logo =
                ".___        __               __________.__         .__\n" +
                        "|   | _____/  |________  ____\\______   \\  | _____  |__| ______ ____\n" +
                        "|   |/    \\   __\\_  __ \\/  _ \\|    |  _/  | \\__  \\ |  |/  ___// __ \\\n" +
                        "|   |   |  \\  |  |  | \\(  <_> )    |   \\  |__/ __ \\|  |\\___ \\\\  ___/\n" +
                        "|___|___|  /__|  |__|   \\____/|______  /____(____  /__/____  >\\___  >\n" +
                        "                \\/                          \\/          \\/        \\/     \\/\n";

        System.out.println("Hello from\n" + logo);

        // init chatbot components
        ChatAction greetings = new Greetings();
        ChatAction echo = new Echo();
        Commands commands = new Commands(greetings, echo);

        // start chatbot
        commands.startChat();
    }
}
