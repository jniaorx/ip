package introblaise.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void taskIntialization_correctDescriptionAndDefaultStatus() {
        Task task = new Task("Finish CS2103T assignment");

        assertEquals("Finish CS2103T assignment", task.description, "Task description should be correctly initialzied.");
        assertFalse(task.isDone, "New task should be marked as not done by default.");
        assertEquals("[ ] Finish CS2103T assignment", task.toString());
    }

    @Test
    public void markAsDone_taskMarkedDone() {
        Task task = new Task("Write JUnit tests");

        task.markAsDone();

        assertTrue(task.isDone, "Task should be marked as done.");
        assertEquals("[X] Write JUnit tests", task.toString(), "Task representation should show completion status.");
    }

    @Test
    public void markAsUndone_taskMarkedUndone() {
        Task task = new Task("Write JUnit tests");

        task.markAsDone();
        task.markAsUndone();

        assertFalse(task.isDone,"Task should be marked as not done.");
        assertEquals("[ ] Write JUnit tests", task.toString(), "Task repreentation should show undone status.");
    }
}
