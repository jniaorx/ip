package introblaise.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTaskAndRetrieve() {
        TaskList taskList = new TaskList();
        int originalSize = taskList.getSize();
        ToDo task = new ToDo("Complete CS2103T");

        taskList.addTask(task);

        assertEquals(originalSize + 1, taskList.getSize(), "TaskList size should be 1 after adding a task.");
        assertEquals(task, taskList.getTask(taskList.getSize() - 1), "Last added task should match.");
    }
}
