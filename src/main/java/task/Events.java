package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Simple constructor for Events that takes in description and time
     * @param description name of the task
     * @param from localdate value as start date
     * @param to localdate value as end date
     */
    public Events(String description, LocalDate from, LocalDate to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Simple constructor for Events that takes in description, time, and boolean value isDone
     * For loading tasks from the save file
     * @param description name of the task
     * @param from localdate value as start date
     * @param to localdate value as end date
     * @param isDone bool value to set isDone value
     */
    public Events(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
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
