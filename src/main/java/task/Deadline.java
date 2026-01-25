package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Simple constructor for Deadline that takes in description and time
     * @param description name of the task
     * @param by localdate value as deadline
     */
    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * Simple constructor for Deadline that takes in description, time, and isDone as bool
     * For loading tasks from the save file
     * @param description name of the task
     * @param by localdate value as deadline
     * @param isDone bool value to set isDone value
     */
    public Deadline(String description, LocalDate by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }




    @Override
    public String toString(){
        String byLine = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byLine + ")";
    }

    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + "//" + by.toString();
    }
}
