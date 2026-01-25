package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodoWithValidValues() {
        Task testTodo = new Todo("Testing");
        assertEquals("Testing", testTodo.description, "Description should be Testing");
        assertEquals(false, testTodo.isDone, "isDone should be false");
    }

    @Test
    public void testEncodeTask() {
        Task testTodo = new Todo("Testing");
        assertEquals("T//0//Testing", testTodo.encodeTask());
    }


}
