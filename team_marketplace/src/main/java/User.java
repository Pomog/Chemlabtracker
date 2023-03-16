public class User {

    private String userID;
    private String username;
    private String password;
    private String name;
    private String email;

    public User (String userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    String getUserID() {return userID;}
    String getUsername() {return username;}
    String getPassword() {return password;}
    String getName() {return name;}
    String getEmail() {return email;}

    void setUserID (String userID) {this.userID = userID;}
    void setUsername (String username) {this.username = username;}
    void setPassword (String password) {this.password = password;}
    void setName (String name) {this.name = name;}
    void setEmail (String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (o == this) {return true;}
        if (!(o instanceof User)) {return false;}
        User user = (User) o;
        return
                userID.equals(user.userID) &&
                        username.equals(user.username) &&
                        password.equals(user.password) &&
                        name.equals(user.name) &&
                        email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * userID.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}