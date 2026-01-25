package roberto;

import task.Task;

import java.util.*;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
        ;
    }

    ;

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        printLine();
        System.out.println(" Hello! I'm Roberto\n" +
                " What can I do for you?");
        printLine();
    }

    public void exit() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();

    }

    public String readCommand() {
        return scanner.nextLine();
    }

    //Prints the whole list
    public void printList(TaskList t) {
        //initialize number for ordering
        int num = 1;
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (Task task : t.getTaskList()) {
            System.out.println(" " + num++ + "." + task);
        }
        printLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void showLoadingError() {
        printLine();
        System.out.println("File either does not exist or is corrupted, generated new file for you!");
        printLine();
    }

    public void deleteMessage(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void addMessage(Task task, int size) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void unmarkMessage(Task task) {
        printLine();
        System.out.println(" Nice! I've unmarked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public void markMessage(Task task) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

}
