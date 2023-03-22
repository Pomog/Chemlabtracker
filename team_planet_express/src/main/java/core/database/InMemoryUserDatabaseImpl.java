package core.database;

import core.domain.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class InMemoryUserDatabaseImpl implements UserDatabase {

    private Long nextId = 1L;
    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        user.setId(nextId);
        nextId++;
        users.add(user);
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
    public List<User> getAllUsers() {
        return users;
    }

}
