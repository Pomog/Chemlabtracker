package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.users.core.domain.User;
import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.RemoveUserResponse;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveUserServiceTest {

    Database database = new InMemoryDatabaseImpl();
    RemoveUserService service = new RemoveUserService(database, new RemoveUserValidator());

    @Test
    public void executeTestTrue() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "a");
        User user1 = new User("A", "a");
        database.add (user1);
        RemoveUserResponse response = service.execute(request);
        assertTrue(response.isUserRemoved());
    }

    @Test
    public void executeTestFalse() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "b");
        User user1 = new User("A", "a");
        database.add (user1);
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isUserRemoved());
    }

    @Test
    public void executeTestNotExist() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "b");
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isUserRemoved());
    }
}