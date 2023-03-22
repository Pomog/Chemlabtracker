package core.services.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
