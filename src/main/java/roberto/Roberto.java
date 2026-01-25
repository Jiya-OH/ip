package roberto;

import exceptions.RobertoException;
import task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Roberto {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;


    /**
     * Simple constructor for Roberto, takes in a string as file path to save
     * @param filePath name of the file to save to
     */
    public Roberto(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadList());
        } catch (IOException | RobertoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Controls the flow of the program based on the user inputs
     */
    public void run(){

        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            String[] inputSplit = input.split(" ", 2);
            try {
                switch (inputSplit[0]) {
                case "bye":
                    isExit = true;
                    break;
                case "list":
                    ui.printList(tasks);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(inputSplit[1]) - 1;
                    Task taskToMark = Parser.parseTaskIndex(markIndex, tasks);
                    tasks.markTask(taskToMark);
                    ui.markMessage(taskToMark);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(inputSplit[1]) - 1;
                    Task taskToUnmark = Parser.parseTaskIndex(unmarkIndex, tasks);
                    tasks.unmarkTask(taskToUnmark);
                    ui.unmarkMessage(taskToUnmark);
                    break;
                case "delete":
                    int index = Integer.parseInt(inputSplit[1]) - 1;
                    Task taskToDelete = Parser.parseTaskIndex(index, tasks);
                    tasks.deleteTask(taskToDelete);
                    ui.deleteMessage(taskToDelete, tasks.getSize());
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
        ui.exit();
    }

    public static void main(String[] args) {
        new Roberto("taskList.txt").run();
    }
}
