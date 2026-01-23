public class Events extends Task{
    private String from;
    private String to;

    public Events(String description, String date){
        super(description);
        String[] interval = date.split("/to ");
        this.from = interval[0];
        this.to = interval[1];
    }

    public Events(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        super.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    @Override
    public String encodeTask() {
        return "E" + super.encodeTask() + "//" + from + "//" + to;
    }
}
