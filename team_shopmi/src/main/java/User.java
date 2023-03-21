import java.util.Objects;

public class User {

    private Long userID;
    private String username;
    private String password;

    public User(Long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public Long getUserID(){
        return userID;
    }
    public void setUserID(Long userID){
        this.userID = userID;
    }

    public String getUsername() {
        return this.username;
    }
    void setUsername (String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    void setPassword (String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, password);
    }

    @Override
    public String toString() {
        return "users.User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}
