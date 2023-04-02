package java2.fitness_app.users.users.core.database;

import java2.fitness_app.users.users.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void add (User user);

    void deleteUser(User user);

    boolean login(Long id, String password);

    List<User> getAllUsers();

    Optional<User> findUserById(Long id);
}
