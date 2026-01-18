public class Events extends Task{
    private String from;
    private String to;

    public Events(String description, String date){
        super(description);
        String[] interval = date.split("/to ");
        this.from = interval[0];
        this.to = interval[1];
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
