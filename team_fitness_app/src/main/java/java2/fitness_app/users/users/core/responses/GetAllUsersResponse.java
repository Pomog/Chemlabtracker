package java2.fitness_app.users.users.core.responses;

import java2.fitness_app.users.users.User;

import java.util.List;

public class GetAllUsersResponse {

    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

}
