package exceptions;

public class UnspecifiedTaskException extends RobertoException {

    public UnspecifiedTaskException() {
        super();
    }

    @Override
    public String getMessage(){
        return "Sorry! Name of the task is not specified, please try again";
    }
}
