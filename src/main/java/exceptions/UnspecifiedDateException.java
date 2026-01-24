package exceptions;

public class UnspecifiedDateException extends RobertoException {
    private String message;
    public UnspecifiedDateException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
