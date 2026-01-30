package exceptions;

/**
 * public class that inherits from robertoexception
 */
public class UnspecifiedDateException extends RobertoException {
    private String message;

    /**
     * Constructor that takes in string
     * @param message message to store in
     */
    public UnspecifiedDateException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
