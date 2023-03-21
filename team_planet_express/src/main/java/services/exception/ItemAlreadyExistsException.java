package services.exception;

public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

}
