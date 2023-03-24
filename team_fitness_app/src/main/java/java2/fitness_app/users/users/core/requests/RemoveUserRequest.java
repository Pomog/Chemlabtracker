package java2.fitness_app.users.users.core.requests;

public class RemoveUserRequest {

    private Long userIdToRemove;

    private String password;

    public RemoveUserRequest(Long userIdToRemove, String password) {
        this.userIdToRemove = userIdToRemove;
        this.password = password;
    }

    public Long getUserIdToRemove() {
        return userIdToRemove;
    }

    public String getPassword() {
        return password;
    }
}
