package database;


import domain.user.User;
import domain.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {

    void save(User user);

    Optional<User> findByRole(UserRole userRole);

    Optional<User> findById(Long itemId);

    Optional<User> findByName(String name);

    List<User> getAllUsers();

}
