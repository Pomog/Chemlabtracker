package core.database;


import core.domain.user.User;
import core.domain.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {

    User save(User user);

    Optional<User> findById(Long itemId);

    Optional<User> findByLogin(String login);

    Optional<User> findByRole(UserRole userRole);

    List<User> getAllUsers();

}
