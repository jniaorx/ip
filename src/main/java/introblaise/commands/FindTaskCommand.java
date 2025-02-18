package introblaise.commands;

import java.util.List;

import introblaise.exceptions.InvalidInputException;
import introblaise.parsers.UtilParser;
import introblaise.task.Task;
import introblaise.task.TaskList;

/**
 * The {@code FindTaskCommand} class implements the {@link TaskCommand} interface
 * and is responsible for handling the "find" command.  This command searches
 * the task list for tasks whose descriptions contain a specified keyword
 * and displays the matching tasks to the user.
 */
public class FindTaskCommand implements TaskCommand {
    private final TaskList taskList;

    /**
     * Constructs a {@code FindTaskCommand} object with the specified {@link TaskList}.
     *
     * @param taskList The {@link TaskList} to search within.
     */
    public FindTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the "find" command.  This method parses the user input to extract
     * the keyword, searches the task list for matching tasks, and then builds a
     * formatted string containing the results.
     *
     * @param userInput The user input string, expected in the format "find x",
     *                  where 'x' is the keyword to search for.
     * @return A string containing the list of matching tasks, or a message
     *         indicating that no matching tasks were found.
     */
    @Override
    public String execute(String userInput) {
        try {
            String keyword = extractKeyword(userInput);
            List<Task> matchingTasks = findMatchingTasks(keyword);

            if (matchingTasks.isEmpty()) {
                return "No tasks found with the keyword: " + keyword;
            } else {
                return buildResponseString(matchingTasks);
            }
        } catch (InvalidInputException | StringIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Extracts the keyword from the user input string.
     *
     * @param userInput The user input string.
     * @return The keyword to search for.
     * @throws InvalidInputException If the user input is invalid or does not contain a keyword.
     */
    private String extractKeyword(String userInput) throws InvalidInputException {
        return UtilParser.parseFindKeyword(userInput);
    }

    /**
     * Searches the task list for tasks whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of {@link Task} objects whose descriptions contain the keyword.
     */
    private List<Task> findMatchingTasks(String keyword) throws InvalidInputException {
        return taskList.findTasksByKeyword(keyword);
    }

    /**
     * Builds a formatted string containing the list of matching tasks.
     *
     * @param matchingTasks The list of {@link Task} objects to format.
     * @return A formatted string containing the list of matching tasks.
     */

    private String buildResponseString(List<Task> matchingTasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:").append("\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            response.append((i + 1)).append(". ").append(task).append("\n");
        }
        return response.toString().trim();
    }
}
