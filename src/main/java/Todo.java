public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String encodeTask() {
        return "T" + super.encodeTask();
    }
}
