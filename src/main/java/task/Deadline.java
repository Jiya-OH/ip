package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        super.isDone = isDone;
    }


    @Override
    public String toString() {
        String byLine = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byLine + ")";
    }

    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + "//" + by.toString();
    }
}
