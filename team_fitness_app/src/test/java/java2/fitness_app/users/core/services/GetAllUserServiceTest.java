package java2.fitness_app.users.core.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.GetAllUsersRequest;
import java2.fitness_app.users.core.responses.GetAllUsersResponse;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUserServiceTest {

    @Mock private Database database;
    @InjectMocks private GetAllUsersService service;

    @Test
    public void shouldGetUsersFromDb(){
        List<User> users = new ArrayList<>();
        users.add(new User("Username", "Password"));
        when(database.getAllUsers()).thenReturn(users);

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getUsers().size(),1);
        assertEquals(response.getUsers().get(0).getUsername(), "Username");
        assertEquals(response.getUsers().get(0).getPassword(), "Password");
    }
}
