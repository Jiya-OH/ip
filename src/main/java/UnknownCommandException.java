public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
    @Override
    public String toString(){
        return "Sorry! This command does not exist, please try again";
    }

}
