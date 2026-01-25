package roberto;

import exceptions.TaskDoesNotExistException;
import exceptions.UnknownCommandException;
import exceptions.UnspecifiedDateException;
import exceptions.UnspecifiedTaskException;
import task.Deadline;
import task.Events;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.format.*;

public class Parser {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Parser(){}

    /**
     * Converts line from save file into a task
     * @param line string to be parsed
     * @return a new task
     */
    public static Task parseTaskLine(String line){
        Task newTask;
        String[] taskString = line.split("//");
        boolean isDone = taskString[1].equals("1");

        switch (taskString[0]) {
            case "T":
                newTask = new Todo(taskString[2], isDone);
                break;
            case "D":
                LocalDate by = LocalDate.parse(taskString[3], formatter);
                newTask = new Deadline(taskString[2], by, isDone);
                break;
            case "E":
                LocalDate from = LocalDate.parse(taskString[3], formatter);
                LocalDate to = LocalDate.parse(taskString[4], formatter);
                newTask = new Events(taskString[2], from, to, isDone);
                break;
            default:
                throw new UnspecifiedTaskException();
        }
        return newTask;
    }

    /**
     * Converts an index based on the list of tasks into a task if it exists
     * @param index index for task list
     * @param t the list of tasks
     * @return a new task
     */
    public static Task parseTaskIndex(int index, TaskList t){
        if (index < 0 || index > t.getTaskList().size() - 1){
            throw new TaskDoesNotExistException(index);
        }
        return t.getTaskList().get(index);
    }

    /**
     * Converts user input into a task
     * @param input command as string
     * @return a new task
     */
    public static Task parseTaskCommand(String input){
        String[] description = input.split(" ", 2);

        Task newTask;
        switch (description[0]) {
            case "todo":
                if (description.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                newTask = new Todo(description[1]);
                break;
            case "deadline":
                if (description.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                String[] descriptionD = description[1].split(" /by ");
                if (descriptionD.length != 2) {
                    throw new UnspecifiedDateException("Sorry! Date is not specified, ensure that only 1 \"/by\" is included after the name of the task ");
                }
                newTask = new Deadline(descriptionD[0], parseLocalDate(descriptionD[1]));
                break;
            case "event":
                if (description.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                String[] descriptionE = description[1].split(" /from ");
                String[] date = descriptionE[1].split(" /to ");
                if (descriptionE.length != 2 || date.length != 2) {
                    throw new UnspecifiedDateException("Sorry! Date is not specified, ensure that only 1 \"/from\" and 1 \"/to\" is included after the name of the task ");
                }
                newTask = new Events(descriptionE[0], parseLocalDate(date[0]), parseLocalDate(date[1]));
                break;
            default:
                throw new UnknownCommandException();
        }
        return newTask;
    }

    /**
     * Converts string into local date
     * @param dateString string to be parsed
     * @return a LocalDate
     */
    public static LocalDate parseLocalDate(String dateString){
        return LocalDate.parse(dateString, formatter);
    }

}
