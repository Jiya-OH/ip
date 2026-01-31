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

    public void robertoGreet() {
        ui.greet();
    }

    public void getResponse(String input) {
        String[] inputSplit = input.split(" ", 2);
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
                Task taskToMark = Parser.parseTaskIndex(markIndex, tasks);
                tasks.markTask(taskToMark);
                ui.markMessage(taskToMark);
                break;
            case "unmark":
                if (inputSplit.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                int unmarkIndex = Integer.parseInt(inputSplit[1]) - 1;
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
