import java.util.*;

public class Roberto {
    public static List<Task> taskList;
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        printLine();
        System.out.println(" Hello! I'm Roberto\n" +
                           " What can I do for you?");
        printLine();
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
        printLine();
    }

    public static void unmarkTask(Task task){
        task.markAsDone(false);
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
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
