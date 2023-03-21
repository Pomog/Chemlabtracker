package services.exception;

public class InvalidLoginNameException extends RuntimeException {

    public InvalidLoginNameException(String errorMessage) {
        super(errorMessage);
    }

}
