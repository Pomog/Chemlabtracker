package bankapp.database;

public interface UsersDatabase {

        void addUsers(User user);
        void deleteUsers(User user);
        List<User> getAllUsers();
    }

