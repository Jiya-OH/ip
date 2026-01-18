public class TaskDoesNotExistException extends IndexOutOfBoundsException {
    private int num;
    public TaskDoesNotExistException(int num) {
        this.num = num + 1;
    }

    @Override
    public String toString() {
        return "Sorry! Task " + num + " does not exist, choose another index";
    }
}
