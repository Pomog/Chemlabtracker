package database;

import java.util.List;

public interface UsersDatabase {

        void addUsers(User user);
        void deleteUsers(User user);
        List<User> getAllUsers();
    }

