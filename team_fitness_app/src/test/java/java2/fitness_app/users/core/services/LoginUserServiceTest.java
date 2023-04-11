package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
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
import static org.mockito.ArgumentMatchers.any;
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
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        LoginUserRequest request = new LoginUserRequest(1L, "Password");
        when(validator.validate(request)).thenReturn(List.of());
        LoginUserResponse response = service.execute(request);
        assertFalse(response.hasErrors());

    }

    @Test
    public void shouldReturnErrorWhenUserIdNotProvided() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
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

    @Test
    public void shouldReturnErrorWhenPasswordNotProvided() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        LoginUserRequest request = new LoginUserRequest(1L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("password", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        LoginUserResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "password");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }
}