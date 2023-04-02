package java2.fitness_app.users.users.core.database;

import java2.fitness_app.users.users.core.domain.User;

import java.util.List;

public interface Database {

    void add (User user);

    boolean deleteUser(Long id, String password);

    boolean login(Long id, String password);

    List<User> getAllUsers();
}
