package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.LoginUserResponse;
import org.junit.Assert;
import org.junit.Test;

public class LoginUserServiceTest {

    Database database = new InMemoryDatabaseImpl();
    LoginUserService service = new LoginUserService(database, new LoginUserValidator());

    @Test
    public void executeTestTrue() {
        LoginUserRequest request = new LoginUserRequest(1L, "a");
        User user1 = new User("A", "a");
        database.add (user1);
        LoginUserResponse response = service.execute(request);
        Assert.assertTrue(response.isUserLogged());
    }

    @Test
    public void executeTestFalse() {
        LoginUserRequest request = new LoginUserRequest(1L, "b");
        User user1 = new User("A", "a");
        database.add (user1);
        LoginUserResponse response = service.execute(request);
        Assert.assertFalse(response.isUserLogged());
    }

    @Test
    public void executeTestNotExist() {
        LoginUserRequest request = new LoginUserRequest(1L, "b");
        LoginUserResponse response = service.execute(request);
        Assert.assertFalse(response.isUserLogged());
    }
}