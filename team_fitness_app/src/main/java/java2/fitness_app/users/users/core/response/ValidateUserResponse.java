package java2.fitness_app.users.users.core.response;


import java.util.List;

public class ValidateUserResponse extends CoreResponse {
    private boolean userValidate;

    public ValidateUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public ValidateUserResponse(boolean userValidate) {
        this.userValidate = userValidate;
    }

    public boolean isUserValidated() {
        return userValidate;
    }
}
