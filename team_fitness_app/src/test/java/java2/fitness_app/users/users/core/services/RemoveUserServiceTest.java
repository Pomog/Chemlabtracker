package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.users.core.domain.User;
import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.RemoveUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveUserServiceTest {


    Database database = new InMemoryDatabaseImpl();
    RemoveUserService service = new RemoveUserService(database, new RemoveUserValidator());

    @Test
    void executeTestTrue() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "a");
        User user1 = new User("A", "a");
        database.add (user1);
        RemoveUserResponse response = service.execute(request);
        assertTrue(response.isUserRemoved());
    }

    @Test
    void executeTestFalse() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "b");
        User user1 = new User("A", "a");
        database.add (user1);
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isUserRemoved());
    }

    @Test
    void executeTestNotExist() {
        RemoveUserRequest request = new RemoveUserRequest(1L, "b");
        RemoveUserResponse response = service.execute(request);
        assertFalse(response.isUserRemoved());
    }
}