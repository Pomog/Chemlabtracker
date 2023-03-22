package core.services.exception;

public class NoOpenCartException extends RuntimeException {

    public NoOpenCartException(String errorMessage) {
        super(errorMessage);
    }

}
