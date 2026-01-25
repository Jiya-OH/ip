package roberto;

import task.Task;

import java.util.*;

public class Ui {
    private Scanner scanner;

    /**
     * Simple constructor for the Ui
     */
    public Ui(){
        scanner = new Scanner(System.in);;
    };

    /**
     * Generates a divider between communication
     */
    public void printLine(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Introduction upon running the program
     */
    public void greet(){
        printLine();
        System.out.println(" Hello! I'm Roberto\n" +
                " What can I do for you?");
        printLine();
    }

    /**
     * Send off message upon exiting the program
     */
    public void exit(){
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();

    }

    /**
     * Reads user input
     * @return Command as string
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Takes in a list of task to print
     * @param t list of tasks
     */
    public void printList(TaskList t){
        //initialize number for ordering
        int num = 1;
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (Task task : t.getTaskList()){
            System.out.println(" " + num++ + "." + task);
        }
        printLine();
    }

    /**
     * Prints out the error messages
     * @param message error message
     */
    public void showError(String message){
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints the loading error message if file is either missing or corrupted
     */
    public void showLoadingError(){
        printLine();
        System.out.println("File either does not exist or is corrupted, generated new file for you!");
        printLine();
    }

    /**
     * Prints the delete message after task is successfully removed
     * @param task task to print in string value
     * @param size current size of the list of task
     */
    public void deleteMessage(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the add message after task is successfully added in
     * @param task task to print in string value
     * @param size current size of task list
     */
    public void addMessage(Task task, int size) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints unmark message after task is successfully unmarked
     * @param task task to print in string value
     */
    public void unmarkMessage(Task task) {
        printLine();
        System.out.println(" Nice! I've unmarked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    /**
     * Prints mark message after task in successfully marked
     * @param task task to print in string value
     */
    public void markMessage(Task task) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

}
