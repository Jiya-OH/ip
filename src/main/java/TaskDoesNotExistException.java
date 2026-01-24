public class TaskDoesNotExistException extends RobertoException {
    private int num;
    public TaskDoesNotExistException(int num) {
        super();
        this.num = num + 1;
    }

    @Override
    public String getMessage() {
        return "Sorry! Task " + num + " does not exist, choose another index";
    }
}
