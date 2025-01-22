// to handle different commands of chatbot
public class Commands {
    private final TaskManager taskManager;

    // constructor
    public Commands(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    // method to execute commands according to userInput
    public void executeCommand(String userInput) {
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
        } else {
            System.out.println("Invalid input.");
        }
    }

    // different command methods
    // method to mark task as done if user inputs "mark x"
    public void markTaskAsDone(String userInput) {
        int taskNo = Integer.parseInt(userInput.substring(5)) - 1;
        Task currTask = taskManager.getTask(taskNo);
        currTask.markAsDone();

        System.out.println("    __________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("        " + currTask);
        System.out.println("    __________________");
    }

    // method to unmark task as undone if user inputs "unmark x"
    public void unmarkTaskAsUndone(String userInput) {
        int taskNo = Integer.parseInt(userInput.substring(7)) - 1;
        Task currTask = taskManager.getTask(taskNo);
        currTask.markAsUndone();

        System.out.println("    __________________");
        System.out.println("    OK, I've marked is task as not done yet:");
        System.out.println("    " + currTask);
        System.out.println("    __________________");
    }

    // method to add to-do task into taskslist if user inputs "todo x"
    public void addTodoTask(String userInput) {
        String description = userInput.substring((5));
        ToDo todoTask = new ToDo(description);
        taskManager.addTask(todoTask);
        int numOfTask = taskManager.getTasksList().size();

        System.out.println("    __________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + todoTask);
        System.out.println("    Now you have " + numOfTask + " tasks in the list." );
        System.out.println("    __________________");
    }

    // method to add deadline task into taskslist if user inputs "deadline x"
    public void addDeadlineTask(String userInput) {
        String description = userInput.substring(9, userInput.indexOf("/"));
        int separator = userInput.indexOf("/") + 4;
        String deadline = userInput.substring(separator);

        Deadline deadlineTask = new Deadline(description, deadline);
        taskManager.addTask(deadlineTask);
        int numOfTask = taskManager.getTasksList().size(); // no of task in task list

        System.out.println("    __________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + deadlineTask);
        System.out.println("    Now you have " + numOfTask + " tasks in the list.");
        System.out.println("    __________________");
    }

    // method to add event task into taskslist if user inputs "event x"
    public void addEventTask(String userInput) {
        String description = userInput.substring(6, userInput.indexOf("/"));
        String[] segments = userInput.substring(6).split("/"); // split description into segments
        String from = segments[1].substring(5);
        String to = segments[2].substring(3);

        Event eventTask = new Event(description, from, to);
        taskManager.addTask(eventTask);
        int numOfTask = taskManager.getTasksList().size(); // no of task in task list

        System.out.println("    __________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + eventTask);
        System.out.println("    Now you have " + numOfTask + " tasks in the list.");
        System.out.println("    __________________");
    }
}
