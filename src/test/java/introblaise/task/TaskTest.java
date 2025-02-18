package introblaise.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Task} class.
 * <p>
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
                task.getDescription(), "Task description should be correctly initialzied.");
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
        assertEquals("[ ] Write JUnit tests", task.toString(),
                "Task representation should show undone status.");
    }

    /**
     * Tests that calling {@link Task#setTag(String)} correctly assigns a tag to a task.
     */
    @Test
    public void tagTask_taskTagged() {
        Task task = new Task("Write JUnit tests");

        task.setTag("urgent");

        assertEquals("urgent", task.getTag(), "Task tag should be correctly assigned.");
        assertEquals("[ ] |urgent| Write JUnit tests", task.toString(), "Task representation should include the tag.");
    }

    /**
     * Tests that calling {@link Task#deleteTag()} correctly removes a tag from a task.
     */
    @Test
    public void removeTag_taskUntagged() {
        Task task = new Task("Write JUnit tests");

        task.setTag("urgent");
        task.deleteTag();

        assertEquals("", task.getTag(), "Task tag should be removed.");
        assertEquals("[ ] Write JUnit tests", task.toString(), "Task representation should no longer include the tag.");
    }

    /**
     * Tests that marking a tagged task as done correctly updates its string representation.
     */
    @Test
    public void markTaggedTaskAsDone_taskMarkedDoneWithTag() {
        Task task = new Task("Write JUnit tests");

        task.setTag("urgent");
        task.markAsDone();

        assertTrue(task.getIsDone(), "Task should be marked as done.");
        assertEquals("[X] |urgent| Write JUnit tests", task.toString(), "Completed task should still display tag.");
    }

    /**
     * Tests that setting a new tag replaces the old tag.
     */
    @Test
    public void setNewTag_oldTagReplaced() {
        Task task = new Task("Write JUnit tests");

        task.setTag("urgent");
        task.setTag("important");

        assertEquals("important", task.getTag(), "New tag should replace the old tag.");
        assertEquals("[ ] |important| Write JUnit tests", task.toString(),
                "Task representation should show the new tag.");
    }

    /**
     * Tests that removing a tag from a completed task correctly updates its string representation.
     */
    @Test
    public void removeTagFromCompletedTask_taskUntagged() {
        Task task = new Task("Write JUnit tests");

        task.setTag("urgent");
        task.markAsDone();
        task.deleteTag();

        assertEquals("[X] Write JUnit tests", task.toString(), "Completed task should no longer display a tag.");
    }
}