import java.util.*;

public class Roberto {
    public static List<String> list;
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
        list.add(input);
        printLine();
        System.out.println(" added: " + input);
        printLine();
    }

    //Prints the whole list
    public static void printList(){
        //initialize number for ordering
        int num = 1;
        printLine();
        for (String line : list){
            System.out.println(" " + num++ + ". " + line);
        }
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
        list = new ArrayList<>();

        greet();

        //Continuously receives input until user inputs "bye"
        while (true){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                addToList(input);
            }
        }

        exit();
    }
}
