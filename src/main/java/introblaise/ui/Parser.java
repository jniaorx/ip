package introblaise.ui;

import introblaise.exceptions.AlreadyUndoneException;
import introblaise.exceptions.InvalidEventFromFormatException;
import introblaise.exceptions.InvalidEventToFormatException;
import introblaise.exceptions.InvalidInputException;
import introblaise.exceptions.InvalidDeadlineFormatException;
import introblaise.exceptions.DeleteEmptyTaskListException;
import introblaise.exceptions.EmptyDescriptionException;
import introblaise.task.ToDo;
import introblaise.task.Deadline;
import introblaise.task.Event;
import introblaise.task.TaskList;
import introblaise.task.Task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.sun.security.jgss.GSSUtil;

/**
 * The Commands class handles the execution fo user commands in the chatbot.
 * It interprets user inputs, executes corresponding operations, and manages tasks
 * using the TaskManager instance.
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Constructor for Commands.
     * @param taskList The TaskManager instance responsible for managing tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes a command based on the user's input.
     * Handles different task operations such as listing, adding, marking, unmarking,and deleting tasks.
     *
     * @param userInput The user's input as a command string.
     */
    public String executeCommand(String userInput) {
        try {
            if (userInput.equalsIgnoreCase("List")) {
                // Displays the task list when "list" is input.
                return taskList.printTaskList();
            } else if (userInput.startsWith("mark")) {
                // Marks a task as done based on its index.
                return markTaskAsDone(userInput);
            } else if (userInput.startsWith("unmark")) {
                // Marks a task as not done based on its index.
                return unmarkTaskAsUndone(userInput);
            } else if (userInput.startsWith("todo")) {
                // Adds a new To-Do task to the list.
                return addTodoTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                // Adds a new introBlaise.task.Deadline task with a specific due date.
                return addDeadlineTask(userInput);
            } else if (userInput.startsWith("event")) {
                // Adds a new introBlaise.task.Event task with a duration.
                return addEventTask(userInput);
            } else if (userInput.startsWith("delete")) {
                // Deletes a task from the list based on its index.
                return deleteTask(userInput);
            } else if (userInput.startsWith("tasks on")) {
                return getTasksOnDate(userInput);
            } else if (userInput.startsWith("find")) {
                return handleFindCommand(userInput);
            } else {
                throw new InvalidInputException("Err...I don't understand this :(. Please give a valid command!");
            }
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input string, expected in the format "mark x" where x is the task number.
     * @throws IndexOutOfBoundsException Exception thrown when format of user input is incorrect.
     * @throws NumberFormatException Exception thrown when number format of user input is incorrect.
     */
    public String markTaskAsDone(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            // Extract task index from the input and mark the task as done.
            int taskNo = Integer.parseInt(userInput.substring(5)) - 1;
            Task currTask = taskList.getTask(taskNo);
            if (taskNo < 0 || taskNo >= taskList.getTasksList().size()) {
                throw new IndexOutOfBoundsException("Invalid task number.");
            }

            currTask.markAsDone();
            taskList.saveTasks();
            // Notify the user that the task is marked as done.a
            return "Well done! I've marked this task as done: " + "\n" + currTask;
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Are you sure you are marking the correct task?";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'unmark'.";
        }
    }

    /**
     * Marks a task as undone based on user input.
     *
     * @param userInput The user input string, expected in the format "unmark x" where x is the task number.
     * @throws IndexOutOfBoundsException Exception thrown when format of user input is incorrect.
     * @throws NumberFormatException Exception thrown when number format of user input is incorrect.
     */
    public String unmarkTaskAsUndone(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            // Extract the task index from the input and mark the task as undone.
            int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
            if (taskNo < 0 || taskNo >= taskList.getTasksList().size()) {
                throw new IndexOutOfBoundsException("Invalid task number.");
            }

            Task currTask = taskList.getTask(taskNo);

            // Error thrown when user tries to unmark an undone task.
            if (!currTask.isDone) {
                throw new AlreadyUndoneException("This task has already been marked undone!");
            }
            currTask.markAsUndone();
            taskList.saveTasks();

            // Notify the user that the task is marked as not done.
            return "OK, I've marked this task as not done yet: " + "\n" + currTask;

        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Are you sure you are unmarking the correct task?";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'unmark'.";
        } catch (AlreadyUndoneException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a To-Do task to the task list.
     *
     * @param userInput The user input string, expected in the format "todo x" where x is the task description.
     * @throws NumberFormatException Exception thrown when number format of user input is incorrect.
     * @throws StringIndexOutOfBoundsException Exception thrown when format of user input is incorrect.
     */
    public String addTodoTask(String userInput) throws NumberFormatException, StringIndexOutOfBoundsException {
        try {
            // Extract the description of the task from the input.
            String description = userInput.substring((5));

            // Error thrown if user does not input description.
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Please enter a description for your task!");
            }

            ToDo todoTask = new ToDo(description);
            taskList.addTask(todoTask);
            int numOfTask = taskList.getTasksList().size();

            // Notify the user that the task has been added.
            StringBuilder response = new StringBuilder();
            response.append("Got it. I've added this task: ").append("\n").append(todoTask).append("\n").
                    append("Now you have ").append(numOfTask).append(" tasks in the list.");
            return response.toString().trim();
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'unmark'.";
        } catch (StringIndexOutOfBoundsException e) {
            return "Errr...Please enter a description";
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param userInput The user input string, expected in  the format deadline x /by date where x is the task description.
     * @throws StringIndexOutOfBoundsException Exception thrown when format of user input is incorrect.
     */
    public String addDeadlineTask(String userInput) throws StringIndexOutOfBoundsException {
        try {
            // Get Deadline task from user input.
            Deadline deadlineTask = getDeadlineTask(userInput);

            // Ensure deadline is valid before adding it to the list
            if (deadlineTask.getDeadline() == null) {
                return "Task was not added due to an invalid deadline.";
            }

            taskList.addTask(deadlineTask);
            int numOfTask = taskList.getTasksList().size();

            // Notify the user that the task has been added.
            StringBuilder response = new StringBuilder();
            response.append("Got it. I've added this task: ").append("\n").append(deadlineTask).append("\n").append("Now you have ").append(numOfTask).append(" tasks in the list.");
            return response.toString().trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "Please enter a description and a deadline for your task!";
        } catch (InvalidDeadlineFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Creates a Deadline task based on user input.
     *
     * @param userInput The user input string, expected in  the format deadline x /by date where x is the task description.
     * @return A Deadline task.
     * @throws InvalidDeadlineFormatException Exception thrown when format of user's input is incorrect.
     */
    private static Deadline getDeadlineTask(String userInput) throws InvalidDeadlineFormatException {
        String description = userInput.substring(8, userInput.indexOf("/")).trim();
        int separator = userInput.indexOf("/") + 4;
        String deadline = userInput.substring(separator);
        if (deadline.isEmpty()) {
            throw new InvalidDeadlineFormatException("There seems to be no deadline entered...? Please enter a deadline after the word /by.");
        }

        return new Deadline(description, deadline);
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param userInput The user input string, expected in the format event x /from date /to date where x is the task description.
     * @throws StringIndexOutOfBoundsException Exception thrown when format of user input is incorrect.
     */
    public String addEventTask(String userInput) throws StringIndexOutOfBoundsException {
        try {
            Event eventTask = getEvent(userInput);

            // Ensure deadline is valid before adding it to the list
            if (eventTask.getFrom() == null || eventTask.getTo() == null) {
                return "Task was not added due to an invalid date and time.";
            }

            taskList.addTask(eventTask);
            int numOfTask = taskList.getTasksList().size(); // no of task in task list

            StringBuilder response = new StringBuilder();
           response.append("Got it. I've added this task: ").append("\n").append(eventTask)
                    .append("\n").append("Now you have ").append(numOfTask).append(" tasks in the list.");
            return response.toString().trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "Please enter a description and a duration for your task!";
        } catch (InvalidEventFromFormatException | InvalidEventToFormatException |
                 EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Creates an Event task based on user input.
     *
     * @param userInput The user input string, expected in  the format event x /from date /to date where x is the task description.
     * @return An introBlaise.task.Event task.
     * @throws EmptyDescriptionException Exception thrown when no description is entered.
     * @throws InvalidEventFromFormatException Exception thrown when format of user input is incorrect.
     * @throws InvalidEventToFormatException Exception thrown when format of user input is incorrect.
     */
    private static Event getEvent(String userInput) throws EmptyDescriptionException, InvalidEventFromFormatException, InvalidEventToFormatException {
        // Extract description from user input.
        String description = userInput.substring(5, userInput.indexOf("/")).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Please enter a description for your task!");
        }
        if (!userInput.contains("/from")) {
            throw new InvalidEventFromFormatException("Please include a 'From' date by using /from!");
        }
        if (!userInput.contains("/to")) {
            throw new InvalidEventToFormatException("Please include a 'To' date by using /to!");
        }

        // Extract from and to date from user input.
        String[] segments = userInput.substring(6).split("/");
        String from = segments[1].substring(4).trim();
        if (from.isEmpty()) {
            throw new InvalidEventFromFormatException("Please include a 'From' date after the word /from!");
        }
        String to = segments[2].substring(2).trim();
        if (to.isEmpty()) {
            throw new InvalidEventToFormatException("Please include a 'To' date after the word /to!");
        }
        return new Event(description, from, to);
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param userInput The user input string, expected in the format delete x where x is the task description.
     * @throws IndexOutOfBoundsException Exception thrown when user is deleting the wrong task.
     * @throws NumberFormatException Exception thrown when number format of user input is incorrect.
     */
    public String deleteTask(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            if (taskList.getTasksList().isEmpty()) {
                throw new DeleteEmptyTaskListException("Your task list is empty. You can't delete anything. "
                        + "Please add tasks.");
            }
            // Extract task from user input.
            int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
            Task currTask = taskList.getTask(taskNo);
            taskList.removeTask(currTask);

            int numOfTask = taskList.getTasksList().size();

            // Notify the user that the task has been deleted.
            StringBuilder response = new StringBuilder();
            response.append("Noted. I've removed this task: ").append("\n").append(currTask)
                    .append("\n").append("Now you have ").append(numOfTask).append(" tasks in the list.");
            return response.toString().trim();
        } catch (DeleteEmptyTaskListException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Invalid index. Have you entered the index? Are you sure "
                    + "you are deleting the correct task?";
        } catch (NumberFormatException e) {
            return "Uh oh! Invalid number. Please enter a number after 'delete'.";
        }
    }

    /**
     * Retrieves tasks from task list on the date based on user input.
     *
     * @param userInput The user input string, expected in the format "tasks on d-MM-yyyy".
     */
    public String getTasksOnDate(String userInput) {
        String dateInput = userInput.substring(9).trim(); // Extract the date part from input
        // Define the expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        try {
            // Convert the string to LocalDate
            LocalDate localDate = LocalDate.parse(dateInput, formatter);

            // Call the method with the parsed LocalDate
            return taskList.printTasksForDate(localDate);
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please enter the date in d-MM-yyyy format.";
        }
    }

    /**
     * Retrieves task from task list that matches the keyword based on user input.
     *
     * @param userInput The user input string, expected in the format find x where x is the keyword.
     */
    public String handleFindCommand(String userInput) {
        String keyword = userInput.substring(5).trim();
        if (keyword.isEmpty()) {
            return "Please provide a keyword to search.";
        }

        List<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            return "No tasks found with the keyword: " + keyword;
        } else {
            StringBuilder response = new StringBuilder();
            response.append("Here are the matching tasks in your list:").append("\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                response.append((i + 1)).append(". ").append(task).append("\n");
            }
            return response.toString().trim();
        }
    }
}
