package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

public class UserMatcher implements ArgumentMatcher<User> {

    private final String name;
    private final String login;
    private final String password;
    private final UserRole userRole;

    public UserMatcher(String name, String login, String password, UserRole userRole) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public boolean matches(User user) {
        return name.equals(user.getName()) &&
                login.equals(user.getLogin()) &&
                password.equals(user.getPassword()) &&
                userRole.equals(user.getUserRole());
    }

}
