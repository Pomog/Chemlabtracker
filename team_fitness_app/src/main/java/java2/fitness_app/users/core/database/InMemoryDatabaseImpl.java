package java2.fitness_app.users.core.database;

import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class InMemoryDatabaseImpl implements Database {

    private static Long nextId = 1L;

    public List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    @Override
    public void add(User user) {
        user.setId(nextId);
        nextId++;
        users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
