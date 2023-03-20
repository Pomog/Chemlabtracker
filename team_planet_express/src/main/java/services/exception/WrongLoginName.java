package services.exception;

public class WrongLoginName extends RuntimeException {

    public WrongLoginName(String errorMessage) {
        super(errorMessage);
    }

}
