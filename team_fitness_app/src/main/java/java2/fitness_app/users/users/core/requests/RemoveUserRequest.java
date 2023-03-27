package java2.fitness_app.users.users.core.requests;

public class RemoveUserRequest {

    private String userIdToRemove;

    private String password;

    public RemoveUserRequest(String userIdToRemove, String password) {
        this.userIdToRemove = userIdToRemove;
        this.password = password;
    }

    public String getUserIdToRemove() {
        return userIdToRemove;
    }

    public String getPassword() {
        return password;
    }
}
