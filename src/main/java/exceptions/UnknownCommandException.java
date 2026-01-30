package exceptions;

/**
 * public class that inherits from robertoexception
 */
public class UnknownCommandException extends RobertoException {
    public UnknownCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Sorry! This command does not exist, please try again";
    }

}
