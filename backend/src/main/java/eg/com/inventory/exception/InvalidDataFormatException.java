package eg.com.inventory.exception;

public class InvalidDataFormatException extends RuntimeException {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}
