package java2.fitness_app.users.core.responses;

public class CoreError {
    private String field;
    private String message;

    public CoreError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

}
