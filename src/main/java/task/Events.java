package task;

import java.time.LocalDate;

import roberto.Parser;

/**
 * public class event that inherits from task
 */
public class Events extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Simple constructor for Events that takes in description and time
     * @param description name of the task
     * @param from localdate value as start date
     * @param to localdate value as end date
     */
    public Events(String description, LocalDate from, LocalDate to) {
        super(description);
        this.fromDate = from;
        this.toDate = to;
    }

    /**
     * Simple constructor for Events that copies from other instance
     * @param other other instance of task
     */
    public Events(Events other) {
        super(other);
        this.fromDate = other.fromDate;
        this.toDate = other.toDate;
    }

    /**
     * Simple constructor for Events that takes in description, time, and boolean value isDone
     * For loading tasks from the save file
     * @param description name of the task
     * @param fromDate localdate value as start date
     * @param toDate localdate value as end date
     * @param isDone bool value to set isDone value
     */
    public Events(String description, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, isDone);
        assert fromDate != null : "fromDate should not be null";
        assert toDate != null : "toDate should not be null";
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    @Override
    public String toString() {
        String fromLine = Parser.convertDate(fromDate);
        String toLine = Parser.convertDate(toDate);
        return "[E]" + super.toString() + " (from: " + fromLine + " to: " + toLine + ")";
    }

    @Override
    public String encodeTask() {
        return "E" + super.encodeTask() + "//" + fromDate.toString() + "//" + toDate.toString();
    }
}
