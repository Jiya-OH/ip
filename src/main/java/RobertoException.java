public class RobertoException extends RuntimeException {
    public RobertoException() {
        super();
    }
    public RobertoException(String message) {
        super(message);
    }

    public String getMessage() {
        return "Roberto encountered an exception, please try again!";
    }
}
