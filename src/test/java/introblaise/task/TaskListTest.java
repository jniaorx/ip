package introblaise.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link TaskList} class.
 *
 * This class tests the functionality of tasks being added into the task list.
 */
public class TaskListTest {
    /**
     * Tests that calling {@link TaskList#addTask(Task)} correctly adds task into
     * the task list by checking the size of the task list.
     */
    @Test
    public void addTask_taskAdded_taskListSizeIncreases() {
        TaskList taskList = new TaskList();
        int originalSize = taskList.getSize();
        ToDo task = new ToDo("Complete CS2103T");

        taskList.addTask(task);

        assertEquals(originalSize + 1, taskList.getSize(), "TaskList size should be 1 after adding a task.");
        assertEquals(task, taskList.getTask(taskList.getSize() - 1), "Last added task should match.");
    }
}
