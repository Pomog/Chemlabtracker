import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserListApplicationTest {

    Database database = new InMemoryDatabaseImpl();

    @Test
    public void registerUser() {
        User user = new User("A", "a");
        database.registerNewUser(user);
        assertEquals(1, database.getAllUsers().size());
    }

    @Test
    public void deleteUser() {
        User user1 = new User("A", "a");
        User user2 = new User("B", "b");
        database.registerNewUser(user1);
        database.registerNewUser(user2);
        database.deleteUser(1L, "a");
        assertEquals(1, database.getAllUsers().size());
    }

    @Test
    public void login() {
        User user1 = new User("A", "a");
        database.registerNewUser(user1);
        assertTrue(database.login(1L, "a"));
    }
    @Test
    public void getUsers() {
        User user1 = new User("A", "a");
        User user2 = new User("B", "b");
        database.registerNewUser(user1);
        database.registerNewUser(user2);
        assertEquals(2, database.getAllUsers().size());
    }
}