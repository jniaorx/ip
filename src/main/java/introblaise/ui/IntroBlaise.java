package introblaise.ui;

import java.util.Map;

import introblaise.commands.CommandFactory;
import introblaise.commands.TaskCommand;
import introblaise.parsers.CommandParser;
import introblaise.storage.Storage;
import introblaise.task.TaskList;

/**
 * The main entry point for the IntroBlaise bot.
 * This class initializes the necessary components such as storage, task management,
 * command processing, and user input handling. It serves as the core interface
 * for processing user commands and returning appropriate responses.
 */
public class IntroBlaise {
    private Storage storage;
    private TaskList taskList;
    private CommandParser commandParser;
    private CommandFactory commandFactory;

    /**
     * Initializes the IntroBlaise bot by setting up storage, task management,
     * and command processing.
     * <p>
     * This constructor initializes:
     * <ul>
     *     <li>{@link Storage} - Manages task persistence.</li>
     *     <li>{@link TaskList} - Handles the list of tasks.</li>
     *     <li>{@link CommandFactory} - Creates and registers available commands.</li>
     *     <li>{@link CommandParser} - Parses and executes user commands.</li>
     * </ul>
     * </p>
     */
    public IntroBlaise() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage);

        this.commandFactory = new CommandFactory(taskList);
        commandFactory.initializeCommandMap();

        Map<String, TaskCommand> commandMap = commandFactory.getCommandMap();
        this.commandParser = new CommandParser(commandMap);
    }

    /**
     * Processes a user command and generates an appropriate response.
     *
     * @param input The user input, typically a command to be processed.
     * @return A string response after the command has been executed.
     */
    public String getResponse(String input) {
        return commandParser.executeCommand(input);
    }
}
