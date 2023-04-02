package java2.fitness_app.users.users.core.responses;

import java2.fitness_app.users.users.core.domain.User;

import java.util.List;

public class AddUserResponse extends CoreResponse {
    private User newUser;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }

}
