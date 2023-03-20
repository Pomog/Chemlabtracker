package services.exception;

public class WrongLoginPassword extends RuntimeException {

    public WrongLoginPassword(String errorMessage) {
        super(errorMessage);
    }

}