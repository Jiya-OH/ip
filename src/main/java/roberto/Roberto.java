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
        ui = new Ui(newWindow);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadList());
        } catch (IOException | RobertoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void handleGreet() {
        ui.greet();
    }

    public void getResponse(String input) {
        String[] inputSplit = input.split(" ", 2);
        assert inputSplit.length > 0 : "inputSplit should have one or more command";
        String command = inputSplit[0];
        try {
            switch (command) {
            case "bye":
                ui.exit();
                break;
            case "list":
                ui.printList(tasks);
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
                break;
            default:
                handleAdd(input);
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

    private void handleMark(String[] inputSplit) throws RobertoException {
        Task task = getTaskFromIndex(inputSplit);
        tasks.markTask(task);
        ui.markMessage(task);
    }

    private void handleUnmark(String[] inputSplit) throws RobertoException {
        Task task = getTaskFromIndex(inputSplit);
        tasks.unmarkTask(task);
        ui.unmarkMessage(task);
    }

    private void handleDelete(String[] inputSplit) throws RobertoException {
        Task task = getTaskFromIndex(inputSplit);
        tasks.deleteTask(task);
        ui.deleteMessage(task, tasks.getSize());
    }

    private void handleFind(String[] inputSplit) throws RobertoException {
        if (inputSplit.length != 2) {
            throw new UnspecifiedTaskException();
        }
        ui.findList(inputSplit[1], tasks.getTaskList());
    }

    private void handleAdd(String input) throws RobertoException {
        Task task = Parser.parseTaskCommand(input);
        assert task != null;

        tasks.addToList(task);
        ui.addMessage(task, tasks.getSize());
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
