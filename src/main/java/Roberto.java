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
        taskList.add(new Task(input));
        printLine();
        System.out.println(" added: " + input);
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
            String[] inputsplit = input.split(" ");
            switch (inputsplit[0]) {
                case "bye":
                    break label;
                case "list":
                    printList();
                    break;
                case "mark":
                    markTask(taskList.get(Integer.parseInt(inputsplit[1]) - 1));
                    break;
                case "unmark":
                    unmarkTask(taskList.get(Integer.parseInt(inputsplit[1]) - 1));
                    break;
                default:
                    addToList(input);
                    break;
            }
        }

        exit();
    }
}
