package services.exception;

public class InvalidLoginPasswordException extends RuntimeException {

    public InvalidLoginPasswordException(String errorMessage) {
        super(errorMessage);
    }

}