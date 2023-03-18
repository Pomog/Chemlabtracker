import java.util.List;

interface UsersDatabase {

    void addUsers(User user);
    void deleteUsers(User user);
    List<User> getAllUsers();
}
