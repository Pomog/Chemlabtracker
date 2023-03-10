package database;

import java.util.List;

public interface Database {

    void registerNewUser(User user);

    boolean deleteUser(Long id, String password);

    boolean login(Long id, String password);

    List<User> getUsers();
}
