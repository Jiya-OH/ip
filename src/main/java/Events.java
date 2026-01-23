import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task{
    private LocalDate from;
    private LocalDate to;


    public Events(String description, LocalDate from, LocalDate to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public Events(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        super.isDone = isDone;
    }


    @Override
    public String toString(){
        String fromLine = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String toLine = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (from: " + fromLine + " to: " + toLine + ")";
    }

    @Override
    public String encodeTask() {
        return "E" + super.encodeTask() + "//" + from.toString() + "//" + to.toString();
    }
}
