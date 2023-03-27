import java2.fitness_app.users.users.User;
import java2.fitness_app.users.users.core.database.Database;
import java2.fitness_app.users.users.core.requests.AddUserRequest;
import java2.fitness_app.users.users.core.responses.CoreError;
import java2.fitness_app.users.users.core.services.AddUserService;
import java2.fitness_app.users.users.core.services.AddUserValidator;
import org.junit.jupiter.api.Test;

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
        AddUserValidator addUserValidator = new AddUserValidator();
        AddUserRequest request = new AddUserRequest(userName, password);
        List<CoreError> errors = addUserValidator.validate(request);
        return errors;
    }
}
