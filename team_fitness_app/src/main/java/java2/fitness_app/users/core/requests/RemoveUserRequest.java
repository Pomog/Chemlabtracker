package java2.fitness_app.users.core.requests;

public class RemoveUserRequest {

    private Long userId;

    private String password;

    public RemoveUserRequest(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
