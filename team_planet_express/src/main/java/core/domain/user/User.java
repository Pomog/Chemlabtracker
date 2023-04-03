package core.domain.user;

import lombok.Data;

import java.util.Objects;

@Data
public class User {

    public static final String BLANK = "";

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

    public User(UserRole userRole) {
        this.name = BLANK;
        this.login = BLANK;
        this.password = BLANK;
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(password, user.password) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, userRole);
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
