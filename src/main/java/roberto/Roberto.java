package roberto;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Stack;

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
    private Stack<String> commands;


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
        commands = new Stack<>();
        try {
            tasks = new TaskList(storage.loadList());
        } catch (IOException | RobertoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        assert tasks != null : "tasks must be initialized";
    }

    public void handleGreet() {
        ui.greet();
    }

    public void getResponse(String input, boolean isUndo) {
        assert input != null : "input must not be null";
        assert tasks != null : "tasks must be initialized";
        assert ui != null : "ui must be initialized";
        assert storage != null : "storage must be initialized";

        boolean isValidToSave = true;

        String[] inputSplit = input.split(" ", 2);
        assert inputSplit.length > 0 : "inputSplit should have one or more command";
        String command = inputSplit[0];
        try {
            switch (command) {
            case "bye":
                ui.exit();
                isValidToSave = false;
                break;
            case "list":
                ui.printList(tasks);
                isValidToSave = false;
                break;
            case "mark":
                handleMark(inputSplit);
                break;
            case "unmark":
                handleUnmark(inputSplit);
                break;
            case "delete":
                handleDelete(inputSplit);
                break;
            case "find":
                handleFind(inputSplit);
                isValidToSave = false;
                break;
            case "undo":
                handleUndo();
                isValidToSave = false;
                break;
            default:
                handleAdd(input);
                break;
            }
        } catch (NumberFormatException e) {
            ui.showError("Sorry! Please input only a number");
            isValidToSave = false;
        } catch (RobertoException e) {
            ui.showError(e.getMessage());
            isValidToSave = false;
        } catch (DateTimeParseException e) {
            ui.showError("Sorry! Wrong date input, please enter in format YYYY-MM-DD");
            isValidToSave = false;
        } finally {
            storage.saveList(tasks);
            recordCommandIfNeeded(input, isValidToSave, isUndo);
        }
    }

    private void recordCommandIfNeeded(String input, boolean shouldRecord, boolean isUndo) {
        if (shouldRecord && !isUndo) {
            commands.push(input);
        }
    }

    private void handleMark(String[] inputSplit) throws RobertoException {
        tasks.saveTaskToHistory();
        Task task = getTaskFromIndex(inputSplit);
        tasks.markTask(task);
        ui.markMessage(task);
    }

    private void handleUnmark(String[] inputSplit) throws RobertoException {
        tasks.saveTaskToHistory();
        Task task = getTaskFromIndex(inputSplit);
        tasks.unmarkTask(task);
        ui.unmarkMessage(task);
    }

    private void handleDelete(String[] inputSplit) throws RobertoException {
        tasks.saveTaskToHistory();
        Task task = getTaskFromIndex(inputSplit);
        tasks.deleteTask(task);
        ui.deleteMessage(task, tasks.getSize());
    }

    private void handleFind(String[] inputSplit) throws RobertoException {
        if (inputSplit.length != 2) {
            throw new UnspecifiedTaskException();
        }
        ui.findList(inputSplit[1], tasks.getTasks());
    }

    private void handleAdd(String input) throws RobertoException {
        tasks.saveTaskToHistory();
        Task task = Parser.parseTaskCommand(input);
        assert task != null;
        tasks.addToList(task);
        ui.addMessage(task, tasks.getSize());
    }

    private void handleUndo() {
        if (commands.isEmpty()) {
            ui.showError("Commands are empty!");
            return;
        }
        String input = commands.pop();
        ui.showUndoMessage(input);
        tasks.undoTaskFromHistory();
        ui.printList(tasks);
    }

    private Task getTaskFromIndex(String[] inputSplit) throws RobertoException {
        if (inputSplit.length != 2) {
            throw new UnspecifiedTaskException();
        }

        int index = Integer.parseInt(inputSplit[1]) - 1;
        assert index >= 0;

        Task task = Parser.parseTaskIndex(index, tasks);
        assert task != null;

        return task;
    }


}
