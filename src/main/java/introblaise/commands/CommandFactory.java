package introblaise.commands;

import java.util.HashMap;
import java.util.Map;

import introblaise.parsers.CommandParser;
import introblaise.task.TaskList;

/**
 * The {@code CommandFactory} class is responsible for creating and registering
 * instances of {@link TaskCommand} implementations. It maintains a map of
 * command names to their corresponding command objects, which can then be
 * accessed by the {@link CommandParser} to execute user commands.  This class
 * uses the provided {@link TaskList} to inject the necessary dependencies
 * into the command objects.
 */
public class CommandFactory {
    private final TaskList taskList;
    private final Map<String, TaskCommand> commandMap;

    /**
     * Constructs a {@code CommandFactory} with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} instance to be used by the commands.
     *                 This is typically the same {@link TaskList} used by the
     *                 main application.
     */
    public CommandFactory(TaskList taskList) {
        this.taskList = taskList;
        this.commandMap = new HashMap<>();
    }

    /**
     * Initializes the command map by registering all available commands.
     * This method creates instances of each {@link TaskCommand} implementation
     * and associates them with their corresponding command names (e.g., "list",
     * "todo", "deadline").  The {@link TaskList} is provided to the commands
     * that require it.
     */
    public void initializeCommandMap() {
        commandMap.put("list", new ListTaskCommand(taskList));
        commandMap.put("mark", new MarkTaskCommand(taskList));
        commandMap.put("unmark", new UnmarkTaskCommand(taskList));
        commandMap.put("todo", new AddToDoTaskCommand(taskList));
        commandMap.put("deadline", new AddDealineTaskCommand(taskList));
        commandMap.put("event", new AddEventTaskCommand(taskList));
        commandMap.put("delete", new DeleteTaskCommand(taskList));
        commandMap.put("tasks", new GetTasksOnDateCommand(taskList));
        commandMap.put("find", new FindTaskCommand(taskList));
        commandMap.put("tag", new TagTaskCommand(taskList));
        commandMap.put("untag", new RemoveTagCommand(taskList));
    }

    /**
     * Returns the map of command names to {@link TaskCommand} objects.
     * This map is used by the {@link CommandParser} to look up and execute
     * commands based on user input.
     *
     * @return The map of commands.
     */
    public Map<String, TaskCommand> getCommandMap() {
        return commandMap;
    }
}
