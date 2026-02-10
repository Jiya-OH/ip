package task;

/**
 * public class todo that inherits from task
 */
public class Todo extends Task {

    /**
     * Simple constructor for Todo that takes in description
     * @param description name of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Simple constructor for Todo that takes in description and boolean value isDone
     * @param description name of the task
     * @param isDone bool value to set isDone value
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Simple constructor for Todo that copies from other instance
     * @param other other instance of task
     */
    public Todo(Todo other) {
        super(other);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encodeTask() {
        return "T" + super.encodeTask();
    }
}
