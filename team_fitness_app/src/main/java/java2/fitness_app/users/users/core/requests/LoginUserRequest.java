package java2.fitness_app.users.users.core.requests;

public class LoginUserRequest {

    private Long userIdToLogin;
    private String password;

    public LoginUserRequest(Long userIdToLogin, String password) {
        this.userIdToLogin = userIdToLogin;
        this.password = password;
    }

    public Long getUserIdToLogin() {
        return userIdToLogin;
    }

    public String getPassword() {
        return password;
    }
}
