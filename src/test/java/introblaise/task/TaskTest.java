package introblaise.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Task} class.
 *
 * This class tests the functionality of task creation, marking tasks as done,
 * and marking them as undone.
 */
public class TaskTest {

    /**
     * Tests that a newly created {@link Task} has the correct description
     * and is marked as not done by default.
     */
    @Test
    public void taskIntialization_correctDescriptionAndDefaultStatus() {
        Task task = new Task("Finish CS2103T assignment");

        assertEquals("Finish CS2103T assignment",
                task.description, "Task description should be correctly initialzied.");
        assertFalse(task.getIsDone(), "New task should be marked as not done by default.");
        assertEquals("[ ] Finish CS2103T assignment", task.toString());
    }

    /**
     * Tests that calling {@link Task#markAsDone()} correctly marks
     * the task as done and updates its string representation.
     */
    @Test
    public void markAsDone_taskMarkedDone() {
        Task task = new Task("Write JUnit tests");

        task.markAsDone();

        assertTrue(task.getIsDone(), "Task should be marked as done.");
        assertEquals("[X] Write JUnit tests", task.toString(), "Task representation should show completion status.");
    }

    /**
     * Tests that calling {@link Task#markAsUndone()} correctly marks
     * the task as not done and updates its string representation.
     */
    @Test
    public void markAsUndone_taskMarkedUndone() {
        Task task = new Task("Write JUnit tests");

        task.markAsDone();
        task.markAsUndone();

        assertFalse(task.getIsDone(), "Task should be marked as not done.");
        assertEquals("[ ] Write JUnit tests", task.toString(), "Task repreentation should show undone status.");
    }
}
