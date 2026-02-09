package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * public class deadline that inherits from task
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Simple constructor for Deadline that takes in description and time
     * @param description name of the task
     * @param byDate localdate value as deadline
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        assert byDate != null : "byDate should not be null";
        this.byDate = byDate;
    }

    /**
     * Simple constructor for Deadline that takes in description, time, and isDone as bool
     * For loading tasks from the save file
     * @param description name of the task
     * @param by localdate value as deadline
     * @param isDone bool value to set isDone value
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.byDate = by;
    }


    @Override
    public String toString() {
        String byLine = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + byLine + ")";
    }

    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + "//" + byDate.toString();
    }
}
