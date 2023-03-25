package core.services.validators.guest;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignUpValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final SignUpRequest mockRequest = mock(SignUpRequest.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final User mockUser = mock(User.class);

    private final SignUpValidator validator = new SignUpValidator(mockDatabase);

    @Test
    void shouldReturnErrorForNullName() {
        when(mockRequest.getName()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankName() {
        when(mockRequest.getName()).thenReturn("");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyName() {
        when(mockRequest.getName()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("name"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNullLoginName() {
        when(mockRequest.getLoginName()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankLoginName() {
        when(mockRequest.getLoginName()).thenReturn("");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyLoginName() {
        when(mockRequest.getLoginName()).thenReturn(" ");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin(" ")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForExistingLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("exists"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForNullPassword() {
        when(mockRequest.getPassword()).thenReturn(null);
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("password"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForBlankPassword() {
        when(mockRequest.getPassword()).thenReturn("");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("password"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForEmptyPassword() {
        when(mockRequest.getPassword()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("password"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("required"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnErrorForShortPassword() {
        when(mockRequest.getPassword()).thenReturn("pa");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("password"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("at least"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockRequest.getName()).thenReturn("name");
        when(mockRequest.getLoginName()).thenReturn("");
        when(mockRequest.getPassword()).thenReturn("p");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getName()).thenReturn("name");
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("pas");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
