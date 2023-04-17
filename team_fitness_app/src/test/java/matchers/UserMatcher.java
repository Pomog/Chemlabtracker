package matchers;
import org.mockito.ArgumentMatcher;
import java2.fitness_app.users.core.domain.User;

public class UserMatcher implements ArgumentMatcher<User> {

    private String username;
    private String password;

    public UserMatcher(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean matches(User user){
        return user.getUsername().equals(username)
                && user.getPassword().equals(password);
    }
}
