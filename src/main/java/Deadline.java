public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone){
        super(description);
        this.by = by;
        super.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + "//" + by;
    }
}
