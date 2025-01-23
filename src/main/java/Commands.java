// to handle different commands of chatbot
public class Commands {
    private final TaskManager taskManager;

    // constructor
    public Commands(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    // method to execute commands according to userInput
    public void executeCommand(String userInput) {
        try {
            if (userInput.equalsIgnoreCase("List")) { // print taskslist if user inputs "list"
                taskManager.printTaskList();
            } else if (userInput.startsWith("mark")) { // mark task as done if user inputs "mark x"
                markTaskAsDone(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTaskAsUndone(userInput);
            } else if (userInput.startsWith("todo")) {
                addTodoTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadlineTask(userInput);
            } else if (userInput.startsWith("event")) {
                addEventTask(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else {
                throw new InvalidInputException("Please give a valid command.");
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // different command methods
    // method to mark task as done if user inputs "mark x"
    public void markTaskAsDone(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            int taskNo = Integer.parseInt(userInput.substring(5)) - 1;
            Task currTask = taskManager.getTask(taskNo);
            currTask.markAsDone();

            System.out.println("    __________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("        " + currTask);
            System.out.println("    __________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid index. Please mark the correct task.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number. Please enter a number after 'unmark'.");
        }
    }

    // method to unmark task as undone if user inputs "unmark x"
    public void unmarkTaskAsUndone(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
            Task currTask = taskManager.getTask(taskNo);

            if (!currTask.isDone) {
                throw new AlreadyUndoneException("This task has already been marked undone!");
            }
            currTask.markAsUndone();

            System.out.println("    __________________");
            System.out.println("    OK, I've marked is task as not done yet:");
            System.out.println("    " + currTask);
            System.out.println("    __________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid index. Please unmark the correct task.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number. Please enter a number after 'unmark'.");
        } catch (AlreadyUndoneException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to add to-do task into taskslist if user inputs "todo x"
    public void addTodoTask(String userInput) throws NumberFormatException, StringIndexOutOfBoundsException {
        try {
            String description = userInput.substring((5));

            ToDo todoTask = new ToDo(description);
            taskManager.addTask(todoTask);
            int numOfTask = taskManager.getTasksList().size();

            System.out.println("    __________________");
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + todoTask);
            System.out.println("    Now you have " + numOfTask + " tasks in the list.");
            System.out.println("    __________________");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number. Please enter a number after 'unmark'.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Please enter a description");
        }
    }

    // method to add deadline task into taskslist if user inputs "deadline x"
    public void addDeadlineTask(String userInput) throws StringIndexOutOfBoundsException {
        try {
            Deadline deadlineTask = getDeadlineTask(userInput);
            taskManager.addTask(deadlineTask);
            int numOfTask = taskManager.getTasksList().size(); // no of task in task list

            System.out.println("    __________________");
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + deadlineTask);
            System.out.println("    Now you have " + numOfTask + " tasks in the list.");
            System.out.println("    __________________");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Please enter a description and a deadline.");
        } catch (InvalidDeadlineFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // method to get deadline task
    private static Deadline getDeadlineTask(String userInput) throws InvalidDeadlineFormatException {
        String description = userInput.substring(8, userInput.indexOf("/")).trim();
        int separator = userInput.indexOf("/") + 4;
        String deadline = userInput.substring(separator);
        if (deadline.isEmpty()) {
            throw new InvalidDeadlineFormatException("Deadline is empty. Please enter a deadline");
        }

        return new Deadline(description, deadline);
    }

    // method to add event task into taskslist if user inputs "event x"
    public void addEventTask(String userInput) throws StringIndexOutOfBoundsException {
        try {
            Event eventTask = getEvent(userInput);
            taskManager.addTask(eventTask);
            int numOfTask = taskManager.getTasksList().size(); // no of task in task list

            System.out.println("    __________________");
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + eventTask);
            System.out.println("    Now you have " + numOfTask + " tasks in the list.");
            System.out.println("    __________________");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Please enter a description and a duration.");
        } catch (InvalidEventFromFormatException | InvalidEventToFormatException | EmptyDescriptionException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    // method to get event
    private static Event getEvent(String userInput) throws EmptyDescriptionException, InvalidEventFromFormatException, InvalidEventToFormatException {
        String description = userInput.substring(5, userInput.indexOf("/")).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Please enter a task description.");
        }
        if (!userInput.contains("/from")) {
            throw new InvalidEventFromFormatException("Please include a 'From' date.");
        }
        if (!userInput.contains("/to")) {
            throw new InvalidEventToFormatException("Please include a 'To' date.");
        }
        String[] segments = userInput.substring(6).split("/"); // split description into segments
        String from = segments[1].substring(4).trim();
        if (from.isEmpty()) {
            throw new InvalidEventFromFormatException("Please include a 'From' date.");
        }
        String to = segments[2].substring(2).trim();
        if (to.isEmpty()) {
            throw new InvalidEventToFormatException("Please include a 'To' date.");
        }
        return new Event(description, from, to);
    }

    // method to delete task from taskslist
    public void deleteTask(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        try {
            if (taskManager.getTasksList().isEmpty()) {
                throw new DeleteEmptyTaskListException("Your taskslist is empty. You can't delete anything. Please add tasks.");
            }
            int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
            Task currTask = taskManager.getTask(taskNo);
            taskManager.removeTask(currTask);

            int numOfTask = taskManager.getTasksList().size();

            System.out.println("    __________________");
            System.out.println("    Noted. I've removed this task:");
            System.out.println("        " + currTask);
            System.out.println("    Now you have " + numOfTask + " tasks in the list.");
            System.out.println("    __________________");
        } catch (DeleteEmptyTaskListException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid index. Please delete the correct task.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number. Please enter a number after 'delete'.");
        }
    }
}
