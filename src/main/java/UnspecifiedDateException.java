public class UnspecifiedDateException extends IndexOutOfBoundsException {
    private String message;
    public UnspecifiedDateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
