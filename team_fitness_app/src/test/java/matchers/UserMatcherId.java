package matchers;

import java2.fitness_app.users.core.domain.User;
import org.mockito.ArgumentMatcher;

public class UserMatcherId implements ArgumentMatcher<User> {

    private Long id;
    private String password;

    public UserMatcherId(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public boolean matches(User user){
        return user.getId().equals(id)
                && user.getPassword().equals(password);
    }
}
