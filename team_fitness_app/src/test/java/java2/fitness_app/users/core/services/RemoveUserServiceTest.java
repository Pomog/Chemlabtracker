package java2.fitness_app.users.core.services;

import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.responses.RemoveUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUserServiceTest {

    @Mock
    private Database database;
    @Mock
    private RemoveUserRequestValidator validator;
    @InjectMocks
    private RemoveUserService service;


    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        RemoveUserRequest notValidRequest = new RemoveUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        RemoveUserResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        RemoveUserRequest notValidRequest = new RemoveUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        RemoveUserResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "username");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        RemoveUserRequest notValidRequest = new RemoveUserRequest(null, "adm123");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("username", "Must not be empty!")));
        service.execute(notValidRequest);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        RemoveUserRequest validRequest = new RemoveUserRequest(1L, "Password");
        when(validator.validate(validRequest)).thenReturn(List.of());
        RemoveUserResponse response = service.execute(validRequest);
        assertFalse(response.hasErrors());
    }


}