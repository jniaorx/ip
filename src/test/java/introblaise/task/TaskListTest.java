package introblaise.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import introblaise.exceptions.EmptyDateException;
import introblaise.storage.Storage;
import introblaise.tasktype.Deadline;
import introblaise.tasktype.Event;
import introblaise.tasktype.ToDo;

/**
 * Unit tests for the {@link TaskList} class.
 * This class tests the functionality of tasks being added into the task list.
 */
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        Storage storage = new Storage();
        taskList = new TaskList(storage);
        taskList.clearTaskList();
    }

    /**
     * Tests that calling {@link TaskList#addTask(Task)} correctly adds task into
     * the task list by checking the size of the task list.
     */
    @Test
    public void addTask_taskAdded_taskListSizeIncreases() {
        int originalSize = taskList.getSize();
        ToDo task = new ToDo("Complete CS2103T");

        taskList.addTask(task);

        assertEquals(originalSize + 1, taskList.getSize(), "TaskList size should be 1 after adding a task.");
        assertEquals(task, taskList.getTask(taskList.getSize() - 1), "Last added task should match.");
    }

    /**
     * Tests that calling {@link TaskList#removeTask(Task)} correctly removes task from
     * the task list by checking the size of the task list.
     */
    @Test
    public void removeTask_taskRemoved_taskListSizeDecreases() {
        ToDo task = new ToDo("Complete CS2103T");
        taskList.addTask(task);

        int originalSize = taskList.getSize();
        taskList.removeTask(task);

        assertEquals(originalSize - 1, taskList.getSize(),
                "TaskList size should be 0 after removing a task.");
    }

    /**
     * Tests that calling {@link TaskList#removeTask(Task)} correctly removes non-existent task into
     * the task list by checking the size of the task list.
     */
    @Test
    public void removeTask_nonExistentTask_taskListSizeRemains() {
        ToDo task1 = new ToDo("Complete CS2103T");
        ToDo task2 = new ToDo("Complete TP");
        taskList.addTask(task1);

        int originalSize = taskList.getSize();
        taskList.removeTask(task2);

        assertEquals(originalSize , taskList.getSize(), "TaskList size should remain the same.");
    }

    /**
     * Tests that calling {@link TaskList#getTask(int)}} correctly retrieves the task based on task index
     * by checking if the task is the same as the added task.
     */
    @Test
    public void getTask_validIndex_returnsCorrectTask() {
        ToDo task = new ToDo("Read a book");
        taskList.addTask(task);
        int taskListSize = taskList.getSize();

        assertEquals(task, taskList.getTask(taskListSize - 1));
    }

    /**
     * Tests that calling {@link TaskList#getTask(int)}} correctly throws IndexOutOfBoundsException
     * when invalid index is entered.
     */
    @Test
    public void getTask_invalidIndex_throwsException() {
        int taskListSize = taskList.getSize();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(taskListSize + 1));
    }

    /**
     * Tests that calling {@link TaskList#printTaskList()} correctly prints the task list
     * by comparing it with a formatted list containing tasks that has been added.
     */
    @Test
    public void printTaskList_nonEmptyTaskList_returnsFormattedList() {
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));

        String expectedOutput = "1. [T][ ] Task 1\n2. [T][ ] Task 2";
        assertEquals(expectedOutput, taskList.printTaskList());
    }

    /**
     * Tests that calling {@link TaskList#printTaskList()}} correctly throws EmptyTaskListException
     * when printing an empty task list.
     */
    @Test
    public void printTaskList_emptyTaskList_returnsEmptyMessage() {
        String expectedMessage = "Oh no! Your task list is empty now. Please add tasks!";
        assertEquals(expectedMessage, taskList.printTaskList());
    }

    /**
     * Tests that calling {@link TaskList#findTasksByKeyword(String)}} correctly returns
     * tasks that matches the keyword by comparing the result list with tasks that matches and tasks that don't.
     */
    @Test
    public void findTasksByKeyword_matchingTasks_returnList() {
        ToDo task1 = new ToDo("Write report");
        ToDo task2 = new ToDo("Read book");
        taskList.addTask(task1);
        taskList.addTask(task2);

        List<Task> results = taskList.findTasksByKeyword("Read");
        assertEquals(1, results.size());
        assertEquals(task2, results.get(0));
    }

    /**
     * Tests that calling {@link TaskList#findTasksByKeyword(String)}} returns nothing when there is no match
     * by making sure the result list is empty.
     */
    @Test
    public void findTasksByKeyword_noMatch_returnsEmptyList() {
        taskList.addTask(new ToDo("Exercise"));

        List<Task> results = taskList.findTasksByKeyword("Study");
        assertTrue(results.isEmpty());
    }

    /**
     * Tests that calling {@link TaskList#printTasksForDate(LocalDate)}} correctly returns
     * tasks that matches the date by comparing them to the expectedOutput string.
     */
    @Test
    public void printTasksForDate_tasksWithMatchingDate_returnsFormattedList() throws EmptyDateException {
        LocalDate localDate = LocalDate.of(2025, 2, 20);
        Deadline deadlineTask = new Deadline("Submit assignment", "20-02-2025 2359");
        Event eventTask = new Event("Project meeting", "20-02-2025 1600", "20-02-2025 1700");
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);

        String expectedOutput = "[D][ ] Submit assignment (by: Feb 20 2025 2359)\n[E][ ] "
                + "Project meeting (from: Feb 20 2025 1600 to: Feb 20 2025 1700)";
        assertEquals(expectedOutput, taskList.printTasksForDate(localDate));
    }

    /**
     * Tests that calling {@link TaskList#printTasksForDate(LocalDate)}} returns
     * empty string when there is no matching tasks.
     */
    @Test
    public void printTasksForDate_noMatchingTasks_returnsEmptyString() throws EmptyDateException {
        LocalDate date = LocalDate.of(2025, 2, 21);
        assertEquals("", taskList.printTasksForDate(date));
    }


}
