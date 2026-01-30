package roberto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.TaskDoesNotExistException;
import task.Task;
import task.Todo;






public class TaskListTest {
    @Test
    public void testDeleteTaskOutOfBounds() {
        TaskList newTaskList = new TaskList();
        Task taskTest = new Todo("testing");
        assertThrows(TaskDoesNotExistException.class, () -> newTaskList.deleteTask(taskTest));
    }

    @Test
    public void testAddToList() {
        TaskList newTaskList = new TaskList();
        assertEquals(0, newTaskList.getSize());
        newTaskList.addToList(new Task("Testing"));
        assertEquals(1, newTaskList.getSize());
    }
}
