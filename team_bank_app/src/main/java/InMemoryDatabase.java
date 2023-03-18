import java.util.ArrayList;
import java.util.List;

class InMemoryUsersDatabase implements UsersDatabase{
    private List<User> users = new ArrayList<>();

    @Override
    public void addUsers(User user) {
        users.add(user);
    }

    @Override
    public void deleteUsers(User user) {
        users.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
