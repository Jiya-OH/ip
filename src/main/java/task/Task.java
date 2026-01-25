package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Simple constructor for Task that takes in description
     * @param description name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Simple constructor for Task that takes in description and boolean value isDone
     * @param description name of the task
     * @param isDone bool value to set isDone value
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns either 'X' or ' ' depending on whether the task is marked
     * @return either 'X' or ' '
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Encodes a task in a string with values separated by '//' for writing onto the
     * save file.
     * @return encoded task string
     */
    public String encodeTask() {
        int isDone = this.isDone ? 1 : 0;
        return "//" + isDone + "//" + description;
    }

    /**
     * Marks a task as unmarked or marked
     * @param status true as marked, false as unmarked
     */
    public void markAsDone(boolean status) {
        isDone = status;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
