package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.domain.User;
import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.responses.LoginUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginUserServiceTest {
    @Mock
    private Database database;
    @Mock
    private LoginUserRequestValidator validator;
    @InjectMocks
    private LoginUserService service;


    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        User user1 = new User("A", "a");
        database.add(user1);
        LoginUserRequest request = new LoginUserRequest(1L, "a");
        when(validator.validate(request)).thenReturn(List.of());
        LoginUserResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnErrorWhenUserIdNotProvided() {
        User user1 = new User("A", "a");
        database.add(user1);
        LoginUserRequest request = new LoginUserRequest(null, "a");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("id", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        LoginUserResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

}