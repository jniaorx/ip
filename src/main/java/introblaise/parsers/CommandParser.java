package introblaise.parsers;

import java.util.Map;

import introblaise.commands.CommandFactory;
import introblaise.commands.TaskCommand;
import introblaise.exceptions.InvalidInputException;

/**
 * The {@code CommandParser} class handles the execution of user commands in the IntroBlaise bot.
 * It interprets user inputs, extracts the command, retrieves the corresponding {@link TaskCommand}
 * from the command map, and executes the command.
 */
public class CommandParser {
    private final Map<String, TaskCommand> commandMap;

    /**
     * Constructs a {@code CommandParser} with a map of commands.
     * This map associates command names with their corresponding {@link TaskCommand} instances.
     *
     * @param commandMap The map of commands to be used by this parser.
     *                   This map is typically provided by a {@link CommandFactory}.
     */
    public CommandParser(Map<String, TaskCommand> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Executes a command based on the user's input.
     * This method parses the user input, extracts the command name,
     * associated {@link TaskCommand} from the command map, and then
     * {@code execute()} method to perform the desired action.
     *
     * @param userInput The user's input string, which should contain the command name
     *                  followed by necessary arguments.
     * @return A string response generated by the executed command, or an error message
     *         if the command is invalid or an error occurs during execution.
     */
    public String executeCommand(String userInput) {
        String command = UtilParser.parseCommand(userInput);
        TaskCommand taskCommand = getCommand(command);

        if (taskCommand == null) {
            return new InvalidInputException("Err...I don't understand this :(. Please give a valid command!")
                    .getMessage();
        }

        return taskCommand.execute(userInput);
    }

    /**
     * Retrieves a {@link TaskCommand} from the command map based on the command name.
     *
     * @param command The name of the command to retrieve.
     * @return The {@link TaskCommand} associated with the given command, or {@code null}
     *         if no such command is found.
     */
    private TaskCommand getCommand(String command) {
        return commandMap.get(command);
    }
}


