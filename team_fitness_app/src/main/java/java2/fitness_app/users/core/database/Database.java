package java2.fitness_app.users.core.database;

import java2.fitness_app.users.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void add(User user);

    void deleteUser(User user);

    List<User> getAllUsers();

    Optional<User> findUserById(Long id);
}
