public class UnspecifiedTaskException extends IndexOutOfBoundsException {
    private String message;

    public UnspecifiedTaskException() {
        super("Sorry! Name of the task is not specified, please try again");
        this.message = "Sorry! Name of the task is not specified, please try again";
    }

    @Override
    public String toString(){
        return message;
    }
}
