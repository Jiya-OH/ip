package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String encodeTask() {
        int isDone = this.isDone ? 1 : 0;
        return "//" + isDone + "//" + description;
    }

    public void markAsDone(boolean status){
        isDone = status;
    }
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
