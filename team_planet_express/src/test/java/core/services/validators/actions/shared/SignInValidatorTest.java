package core.services.validators.actions.shared;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.services.exception.ServiceMissingDataException;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignInValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final SignInRequest mockRequest = mock(SignInRequest.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final User mockUser = mock(User.class);
    private final MutableLong mockUserId = mock(MutableLong.class);

    private final SignInValidator validator = new SignInValidator(mockDatabase);

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
    void shouldReturnErrorForNonexistentLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("does not exist"))
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
    void shouldReturnErrorForWrongPassword() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("wrong password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        when(mockUser.getPassword()).thenReturn("password");
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("password"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("incorrect"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockRequest.getLoginName()).thenReturn(null);
        when(mockRequest.getPassword()).thenReturn(" ");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        when(mockUser.getPassword()).thenReturn("password");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldThrowExceptionForMissingOptional() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser), Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> validator.validate(mockRequest));
    }

}
