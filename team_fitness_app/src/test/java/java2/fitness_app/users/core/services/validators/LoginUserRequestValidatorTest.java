package java2.fitness_app.users.core.services.validators;

import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.services.LoginUserRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoginUserRequestValidatorTest {

    private LoginUserRequestValidator validator = new LoginUserRequestValidator();

    @Test
    public void idIsBlank() {
        LoginUserRequest request = new LoginUserRequest(null, "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

    }

    @Test
    public void passwordIsBlank() {
        LoginUserRequest request = new LoginUserRequest(1L, " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void idAndPasswordAreBlank() {
        LoginUserRequest request = new LoginUserRequest(null, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "password");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

    }

    @Test
    public void idAndPasswordAreValid() {
        LoginUserRequest request = new LoginUserRequest(1L, "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);

    }

}
