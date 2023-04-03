import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.core.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UserListApplicationTest {

    Database database = new InMemoryDatabaseImpl();

    @Test
    public void registerUser() {
        User user = new User("A", "a");
        database.add (user);
        assertEquals(1, database.getAllUsers().size());
    }

    @Test
    public void deleteUser() {
        User user1 = new User("A", "a");
        User user2 = new User("B", "b");
        database.add (user1);
        database.add (user2);
        database.deleteUser(user1);
        assertEquals(1, database.getAllUsers().size());
    }

    @Test
    public void login() {
        User user1 = new User("A", "a");
        database.add (user1);
        assertTrue(database.login(1L, "a"));
    }
    @Test
    public void getUsers() {
        User user1 = new User("A", "a");
        User user2 = new User("B", "b");
        database.add (user1);
        database.add (user2);
        assertEquals(2, database.getAllUsers().size());
    }
}