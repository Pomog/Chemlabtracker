import java.util.List;

public interface Database {

    void registerNewUser(User user);

    void deleteUser(Long id, String password);

    boolean login(Long id, String password);

    List<User> getUsers();
}
