package roberto;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import exceptions.RobertoException;
import exceptions.UnspecifiedTaskException;
import task.Task;



/**
 * Public class for roberto, where main method reside in
 */
public class Roberto {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;


    /**
     * Simple constructor for Roberto, takes in a string as file path to save
     *
     * @param filePath name of the file to save to
     */
    public Roberto(String filePath, MainWindow newWindow) {
        assert filePath != null && !filePath.isEmpty() : "filePath must be non-null and non-empty";
        assert newWindow != null : "MainWindow must not be null";

        ui = new Ui(newWindow);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadList());
        } catch (IOException | RobertoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        assert tasks != null : "tasks must be initialized";
    }

    public void robertoGreet() {
        ui.greet();
    }

    public void getResponse(String input) {
        assert input != null : "input must not be null";
        assert tasks != null : "tasks must be initialized";
        assert ui != null : "ui must be initialized";
        assert storage != null : "storage must be initialized";

        String[] inputSplit = input.split(" ", 2);
        assert inputSplit.length > 0 : "inputSplit's length should be more than 0";
        try {
            switch (inputSplit[0]) {
            case "bye":
                ui.exit();
                break;
            case "list":
                ui.printList(tasks);;
                break;
            case "mark":
                if (inputSplit.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                int markIndex = Integer.parseInt(inputSplit[1]) - 1;
                assert markIndex >= 0 : "Task index must be non-negative";
                Task taskToMark = Parser.parseTaskIndex(markIndex, tasks);
                tasks.markTask(taskToMark);
                ui.markMessage(taskToMark);
                break;
            case "unmark":
                if (inputSplit.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                int unmarkIndex = Integer.parseInt(inputSplit[1]) - 1;
                assert unmarkIndex >= 0 : "Task index must be non-negative";
                Task taskToUnmark = Parser.parseTaskIndex(unmarkIndex, tasks);
                tasks.unmarkTask(taskToUnmark);
                ui.unmarkMessage(taskToUnmark);
                break;
            case "delete":
                if (inputSplit.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                int index = Integer.parseInt(inputSplit[1]) - 1;
                Task taskToDelete = Parser.parseTaskIndex(index, tasks);
                tasks.deleteTask(taskToDelete);
                ui.deleteMessage(taskToDelete, tasks.getSize());
                break;
            case "find":
                if (inputSplit.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                ui.findList(inputSplit[1], tasks.getTaskList());
                break;
            default:
                Task taskToAdd = Parser.parseTaskCommand(input);
                assert taskToAdd != null : "Parser must return a valid Task";
                tasks.addToList(taskToAdd);
                ui.addMessage(taskToAdd, tasks.getSize());
                break;
            }
        } catch (NumberFormatException e) {
            ui.showError("Sorry! Please input only a number");
        } catch (RobertoException e) {
            ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showError("Sorry! Wrong date input, please enter in format YYYY-MM-DD");
        } finally {
            storage.saveList(tasks);
        }
    }


}
