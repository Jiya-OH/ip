package roberto;

import java.util.List;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import task.Task;

/**
 * public class Ui for user interface
 */
public class Ui {
    private MainWindow mainWindow;

    /**
     * Simple constructor for the Ui
     */
    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        assert mainWindow != null : "mainWindow must be initialized";
    }


    /**
     * Introduction upon running the program
     */
    public void greet() {
        mainWindow.addRobertoDialog(" Hello! I'm Roberto\n"
                + " What can I do for you?");
    }

    /**
     * Send off message upon exiting the program
     */
    public void exit() {
        assert mainWindow != null : "mainWindow must be initialized";
        mainWindow.addRobertoDialog(" Bye. Hope to see you again soon!");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        // Set the action to perform after the pause finishes
        pause.setOnFinished(event -> {
            mainWindow.exit();
        });
        pause.play();
    }

    /**
     * Takes in a list of task to print
     * @param taskList list of tasks
     */
    public void printList(TaskList taskList) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert taskList != null : "taskList must not be null";
        StringBuilder sb = new StringBuilder();
        //initialize number for ordering
        int num = 1;
        sb.append(" Here are the tasks in your list:\n");
        for (Task task : taskList.getTasks()) {
            sb.append(" ").append(num++).append(".").append(task).append("\n");
        }
        mainWindow.addRobertoDialog(sb.toString());
    }

    /**
     * Prints out the error messages
     * @param message error message
     */
    public void showError(String message) {
        assert mainWindow != null : "mainWindow must be initialized";
        mainWindow.addRobertoDialog(message);
    }

    /**
     * Prints out undo message
     * @param message undo message
     */
    public void showUndoMessage(String message) {
        assert mainWindow != null : "mainWindow must be initialized";
        mainWindow.addRobertoDialog("Undoing command:\n" + message);
    }

    /**
     * Prints the loading error message if file is either missing or corrupted
     */
    public void showLoadingError() {
        assert mainWindow != null : "mainWindow must be initialized";
        mainWindow.addRobertoDialog("File either does not exist or is corrupted, "
                + "generated new file for you!");
    }

    /**
     * Prints the delete message after task is successfully removed
     * @param task task to print in string value
     * @param size current size of the list of task
     */
    public void deleteMessage(Task task, int size) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert task != null : "task must not be null";
        assert size >= 0 : "size should not be negative";
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task).append("\n");
        sb.append(" Now you have ").append(size).append(" tasks in the list.");
        mainWindow.addRobertoDialog(sb.toString());
    }

    /**
     * Prints the add message after task is successfully added in
     * @param task task to print in string value
     * @param size current size of task list
     */
    public void addMessage(Task task, int size) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert task != null : "task must not be null";
        assert size >= 0 : "size should not be negative";
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've added this task:\n");
        sb.append(task).append("\n");
        sb.append(" Now you have ").append(size).append(" tasks in the list.");
        mainWindow.addRobertoDialog(sb.toString());
    }

    /**
     * Prints unmark message after task is successfully unmarked
     * @param task task to print in string value
     */
    public void unmarkMessage(Task task) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert task != null : "task must not be null";
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append("  ").append(task);
        mainWindow.addRobertoDialog(sb.toString());
    }

    /**
     * Prints mark message after task in successfully marked
     * @param task task to print in string value
     */
    public void markMessage(Task task) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert task != null : "task must not be null";
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  ").append(task);
        mainWindow.addRobertoDialog(sb.toString());
    }

    /**
     * Finds list of tasks that match search keyword
     * @param search keyword to find tasks
     * @param taskList whoe list of tasks
     */
    public void findList(String search, List<Task> taskList) {
        assert mainWindow != null : "mainWindow must be initialized";
        assert search != null : "search must not be null";
        assert !search.isEmpty() : "search must not be empty";
        assert taskList != null : "taskList should not be null";
        StringBuilder sb = new StringBuilder();
        int num = 1;
        sb.append("Here are the matching tasks in your list:\n");
        for (Task task : taskList) {
            if (task.getDescription().contains(search)) {
                sb.append(" ").append(num++).append(".").append(task).append("\n");
            }
        }
        mainWindow.addRobertoDialog(sb.toString());
    }

}
