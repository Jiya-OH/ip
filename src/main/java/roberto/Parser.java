package roberto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.TaskDoesNotExistException;
import exceptions.UnknownCommandException;
import exceptions.UnspecifiedDateException;
import exceptions.UnspecifiedTaskException;
import task.Deadline;
import task.Events;
import task.Task;
import task.Todo;



/**
 * Public class for parser, to parse strings into various class instances
 */
public class Parser {
    private static DateTimeFormatter INPUTDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter OUTPUTDATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final String DELIMITER = "//";

    public Parser() {
    }

    /**
     * Converts line from save file into a task
     * @param line string to be parsed
     * @return a new task
     */
    public static Task parseTaskLine(String line) {
        String[] taskString = line.split(DELIMITER);
        String taskType = taskString[0];
        boolean isDone = taskString[1].equals("1");

        switch (taskType) {
        case "T":
            return new Todo(taskString[2],
                    isDone);
        case "D":
            return new Deadline(taskString[2],
                    parseLocalDate(taskString[3]),
                    isDone);
        case "E":
            return new Events(taskString[2],
                    parseLocalDate(taskString[3]),
                    parseLocalDate(taskString[4]),
                    isDone);
        default:
            throw new UnspecifiedTaskException();
        }
    }

    /**
     * Converts an index based on the list of tasks into a task if it exists
     * @param index index for task list
     * @param t the list of tasks
     * @return a new task
     */
    public static Task parseTaskIndex(int index, TaskList t) {
        if (index < 0 || index > t.getTasks().size() - 1) {
            throw new TaskDoesNotExistException(index);
        }
        return t.getTasks().get(index);
    }

    /**
     * Converts user input into a task
     * @param input command as string
     * @return a new task
     */
    public static Task parseTaskCommand(String input) {
        String[] tokens = input.split(" ", 2);
        assert tokens.length > 0 : "tokens should have more than 0";
        String command = tokens[0];
        switch (command) {
        case "todo":
            return parseTodo(tokens);
        case "deadline":
            return parseDeadline(tokens);
        case "event":
            return parseEvent(tokens);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * parse string of tokens into task Todo
     * @param tokens complete list of tokens
     * @return Todo task
     */
    private static Task parseTodo(String[] tokens) {
        checkDescription(tokens);
        return new Todo(tokens[1]);
    }

    /**
     * parse string of tokens into task Deadline
     * @param tokens complete list of tokens
     * @return Deadline task
     */
    private static Task parseDeadline(String[] tokens) {
        checkDescription(tokens);

        String[] parts = tokens[1].split(" /by ");
        checkSplit(parts, "/by");

        return new Deadline(parts[0], parseLocalDate(parts[1]));
    }

    /**
     * Parses string of tokens into task Event
     * @param tokens complete list of tokens
     * @return Event task
     */
    private static Task parseEvent(String[] tokens) {
        checkDescription(tokens);

        String[] firstSplit = tokens[1].split(" /from ");
        checkSplit(firstSplit, "/from");

        String[] dateSplit = firstSplit[1].split(" /to ");
        checkSplit(dateSplit, "/to");

        return new Events(
                firstSplit[0],
                parseLocalDate(dateSplit[0]),
                parseLocalDate(dateSplit[1])
        );
    }

    /**
     * Checks if description exists by finding the length of the array of strings tokens
     * @param tokens Complete list of tokens
     */
    private static void checkDescription(String[] tokens) {
        if (tokens.length != 2) {
            throw new UnspecifiedTaskException();
        }
    }

    /**
     * Checks if date is specified when the string is split by a delimiter
     * @param parts complete list of string as parts
     * @param delimiter delimiter that splits the string
     */
    private static void checkSplit(String[] parts, String delimiter) {
        if (parts.length != 2) {
            throw new UnspecifiedDateException(
                    "Sorry! Date is not specified correctly. "
                            + "Ensure exactly one \"" + delimiter + "\" is used."
            );
        }
    }

    /**
     * Converts string into local date
     * @param dateString string to be parsed
     * @return a LocalDate
     */
    public static LocalDate parseLocalDate(String dateString) {
        assert dateString != null && !dateString.isEmpty();
        return LocalDate.parse(dateString, INPUTDATE_FORMATTER);
    }

    public static String convertDate(LocalDate date) {
        return date.format(OUTPUTDATE_FORMATTER);
    }

}
