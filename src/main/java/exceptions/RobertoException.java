package exceptions;

/**
 * public class that inherits from runtimeexception
 */
public class RobertoException extends RuntimeException {
    public RobertoException() {
        super();
    }

    public RobertoException(String message) {
        super(message);
    }

    public String getMessage() {
        return "roberto.Roberto encountered an exception, please try again!";
    }
}
