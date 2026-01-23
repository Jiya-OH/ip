import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Roberto {
    public static List<Task> taskList;

    public static String saveFileString = "./data/taskList.txt";
    public static Path filePath;

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        printLine();
        System.out.println(" Hello! I'm Roberto\n" +
                           " What can I do for you?");
        printLine();
    }

    public static void saveList()  {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : taskList) {
                sb.append(t.encodeTask()).append("\n");
            }
            Files.writeString(filePath, sb.toString());
        } catch (IOException e){
            System.out.println("Error! Can't save task list to file");
        }
    }

    public static void parseTaskLine(String line){

        String[] taskString = line.split("//");
        boolean isDone = taskString[1].equals("1");

        switch (taskString[0]) {
            case "T":
                taskList.add(new Todo(taskString[2], isDone));
                break;
            case "D":
                taskList.add(new Deadline(taskString[2], taskString[3], isDone));
                break;
            case "E":
                taskList.add(new Events(taskString[2], taskString[3], taskString[4], isDone));
                break;
            default:
                System.out.println("Current task parsed is unknown.");
                break;
        }
    }

    public static void loadList() {
        try (Stream<String> streamTask = Files.lines(filePath)){
            streamTask.forEach(Roberto::parseTaskLine);
        } catch (IOException e){
            System.out.println("Error! File is corrupted, I'll reset the list for you");
            taskList.clear();
            saveList();
        }
    }

    //Adds input line to list
    public static void addToList(String input) {
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
                String[] descriptionD = description[1].split("/by ");
                if (descriptionD.length != 2) {
                    throw new UnspecifiedDateException("Sorry! Date is not specified, ensure that only 1 \"/by\" is included after the name of the task ");
                }
                newTask = new Deadline(descriptionD[0], descriptionD[1]);
                break;
            case "event":
                if (description.length != 2) {
                    throw new UnspecifiedTaskException();
                }
                String[] descriptionE = description[1].split("/from ");
                if (descriptionE.length != 2) {
                    throw new UnspecifiedDateException("Sorry! Date is not specified, ensure that only 1 \"/from\" and 1 \"/to\" is included after the name of the task ");
                }
                newTask = new Events(descriptionE[0], descriptionE[1]);
                break;
            default:
                throw new UnknownCommandException("Sorry! I don't know what you mean");
        }
        taskList.add(newTask);
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        saveList();
        printLine();
    }

    //Prints the whole list
    public static void printList(){
        //initialize number for ordering
        int num = 1;
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (Task task : taskList){
            System.out.println(" " + num++ + "." + task);
        }
        printLine();
    }

    public static void markTask(Task task){
        task.markAsDone(true);
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task);
        saveList();
        printLine();
    }

    public static void deleteTask(int index){
        index -= 1;
        if (index < 0 || index > taskList.size() - 1){
            throw new TaskDoesNotExistException(index);
        }

        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index));
        taskList.remove(index);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        saveList();
        printLine();
    }

    public static void unmarkTask(Task task){
        task.markAsDone(false);
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        saveList();
        printLine();
    }

    public static void exit(){
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //initializes new list
        taskList = new ArrayList<>();
        filePath = Paths.get(saveFileString);
        if (Files.exists(filePath)) {loadList();}


        greet();



        //Continuously receives input until user inputs "bye"
        label:
        while (true) {
            String input = scanner.nextLine();
            String[] inputsplit = input.split(" ", 2);
            try {
                switch (inputsplit[0]) {
                    case "bye":
                        break label;
                    case "list":
                        printList();
                        break;
                    case "mark":
                        int markindex = Integer.parseInt(inputsplit[1]) - 1;
                        if (markindex < 0 || markindex > taskList.size() - 1){
                            throw new TaskDoesNotExistException(markindex);
                        }
                        markTask(taskList.get(markindex));
                        break;
                    case "unmark":
                        int unmarkindex = Integer.parseInt(inputsplit[1]) - 1;
                        if (unmarkindex < 0 || unmarkindex > taskList.size() - 1){
                            throw new TaskDoesNotExistException(unmarkindex);
                        }
                        unmarkTask(taskList.get(Integer.parseInt(inputsplit[1]) - 1));
                        break;
                    case "delete":
                        deleteTask(Integer.parseInt(inputsplit[1]));
                        break;
                    default:
                        addToList(input);
                        break;
                }
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("Sorry! Please input only a number");
                printLine();
            } catch (UnknownCommandException e){
                printLine();
                System.out.println(e);
                printLine();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                System.out.println(e);
                printLine();
            }
        }

        exit();
    }
}
