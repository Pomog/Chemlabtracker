package core.database;

import core.domain.user.User;
import core.domain.user.UserRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class InMemoryUserDatabaseImpl implements UserDatabase {

    private Long nextId = 1L;
    private final List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        user.setId(nextId);
        nextId++;
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long itemId) {
        return users.stream()
                .filter(user -> user.getId().equals(itemId))
                .findFirst();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> findByRole(UserRole userRole) {
        return users.stream()
                .filter(user -> user.getUserRole().equals(userRole))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

}
