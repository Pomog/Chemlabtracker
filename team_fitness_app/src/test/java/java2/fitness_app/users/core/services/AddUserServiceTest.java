package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.requests.AddUserRequest;
import java2.fitness_app.users.core.responses.AddUserResponse;
import java2.fitness_app.users.core.responses.CoreError;
import matchers.UserMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {

    @Mock private Database database;
    @Mock private AddUserRequestValidator validator;
    @InjectMocks private AddUserService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails(){
        AddUserRequest notValidRequest = new AddUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        AddUserResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }
    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator(){
        AddUserRequest notValidRequest = new AddUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        AddUserResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(), "username");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails(){
        AddUserRequest notValidRequest = new AddUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        service.execute(notValidRequest);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldAddUserToDatabaseWhenRequestIsValid(){
        AddUserRequest validRequest = new AddUserRequest("Username", "Password");
        when(validator.validate(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(database).add(argThat(new UserMatcher("Username", "Password")));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid(){
        AddUserRequest validRequest = new AddUserRequest("Username", "Password");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddUserResponse response = service.execute(validRequest);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWhitUserWhenRequestIsValid(){
        AddUserRequest validRequest = new AddUserRequest("Username", "Password");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddUserResponse response = service.execute(validRequest);
        assertNotNull(response.getNewUser());
        assertEquals(response.getNewUser().getUsername(), validRequest.getUsername());
        assertEquals(response.getNewUser().getPassword(), validRequest.getPassword());
    }

}
