package services.exception;

public class CartIsEmptyException extends RuntimeException {

    public CartIsEmptyException(String errorMessage) {
        super(errorMessage);
    }

}
