package core.services.exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String errorMessage) {
        super(errorMessage);
    }

}
