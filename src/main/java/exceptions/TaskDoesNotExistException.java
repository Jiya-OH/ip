package exceptions;

/**
 * public class that inherits from robertoexception
 */
public class TaskDoesNotExistException extends RobertoException {
    private int num;

    /**
     * Constructor that takes in integer
     * @param num index user inputted
     */
    public TaskDoesNotExistException(int num) {
        super();
        this.num = num + 1;
    }

    @Override
    public String getMessage() {
        return "Sorry! Task " + num + " does not exist, choose another index";
    }
}
