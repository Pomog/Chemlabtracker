package java2.fitness_app.users.core.services.validators;

import java2.fitness_app.users.core.requests.AddUserRequest;
import java2.fitness_app.users.core.responses.CoreError;
import java2.fitness_app.users.core.services.AddUserRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddUserServiceValidatorTest {

    @Test
    public void userNameIsBlank(){
        List<CoreError> errors = prepareRequest("", "adm123");
        assertEquals("username", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void passwordIsBlank(){
        List<CoreError> errors = prepareRequest("Bill", "");
        assertEquals("password", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    public void userNameAndPasswordAreBlank(){
        List<CoreError> errors = prepareRequest("", "");
        assertEquals("username", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());

        assertEquals("password", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void userNameAndPasswordAreValid(){
        List<CoreError> errors = prepareRequest("Bill", "adm123");
        assertTrue(errors.isEmpty());
    }

    public List<CoreError> prepareRequest(String userName, String password){
        AddUserRequestValidator addUserRequestValidator = new AddUserRequestValidator();
        AddUserRequest request = new AddUserRequest(userName, password);
        List<CoreError> errors = addUserRequestValidator.validate(request);
        return errors;
    }
}
