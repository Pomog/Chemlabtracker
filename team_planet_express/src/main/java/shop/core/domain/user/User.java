package shop.core.domain.user;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String login;
    private String password;
    private UserRole userRole;

    public User(String name, String login, String password, UserRole userRole) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole;
    }

}
