package java2.fitness_app.users.users.core.requests;

public class AddUserRequest {
    private String username;
    private String password;

    public AddUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
