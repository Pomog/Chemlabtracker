package core.services.validators.actions.guest;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.services.validators.universal.system.MutableLongUserIdValidator;
import core.services.validators.universal.user_input.PresenceValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SignUpValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final MutableLongUserIdValidator mockMutableLongUserIdValidator = mock(MutableLongUserIdValidator.class);
    private final PresenceValidator mockPresenceValidator = mock(PresenceValidator.class);
    private final SignUpRequest mockRequest = mock(SignUpRequest.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final MutableLong mockUserId = mock(MutableLong.class);
    private final User mockUser = mock(User.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final SignUpValidator validator =
            new SignUpValidator(mockDatabase, mockMutableLongUserIdValidator, mockPresenceValidator);

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        validator.validate(mockRequest);
        verify(mockMutableLongUserIdValidator).validateMutableLongUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateNamePresence() {
        when(mockRequest.getName()).thenReturn("name");
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("name", "name", "Name");
    }

    @Test
    void shouldValidateLoginNamePresence() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("login name", "login", "Login name");
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
    void shouldValidatePasswordPresence() {
        when(mockRequest.getPassword()).thenReturn("password");
        validator.validate(mockRequest);
        verify(mockPresenceValidator).validateStringIsPresent("password", "password", "Password");
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
        when(mockRequest.getName()).thenReturn("");
        when(mockRequest.getLoginName()).thenReturn("");
        when(mockRequest.getPassword()).thenReturn("p");
        when(mockPresenceValidator.validateStringIsPresent("", "name", "Name")).thenReturn(Optional.of(mockCoreError));
        when(mockPresenceValidator.validateStringIsPresent("", "login name", "Login Name")).thenReturn(Optional.of(mockCoreError));
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
        when(mockPresenceValidator.validateStringIsPresent(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
