import java.util.*;

public class Roberto {
    public static void greet(){
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Roberto\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("____________________________________________________________\n" + input);
        System.out.println("____________________________________________________________");
    }

    public static void exit(){
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                break;
            } else {
                echo(input);
            }
        }
        exit();
    }
}
