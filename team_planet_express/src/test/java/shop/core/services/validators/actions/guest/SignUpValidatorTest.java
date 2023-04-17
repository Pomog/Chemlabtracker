package shop.core.services.validators.actions.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Database;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import shop.core.support.CurrentUserId;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private CurrentUserIdValidator mockCurrentUserIdValidator;
    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private SignUpRequest mockRequest;
    @Mock private CurrentUserId mockUserId;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private User mockUser;
    @Mock private CoreError mockCoreError;

    @InjectMocks private SignUpValidator validator;

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        validator.validate(mockRequest);
        verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateNameIsPresent() {
        when(mockRequest.getName()).thenReturn("name");
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("name", "name", "Name");
        verify(mockInputStringValidator).validateIsPresent(record);
    }

    @Test
    void shouldValidateLoginNameIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("login name", "login", "Login name");
        verify(mockInputStringValidator).validateIsPresent(record);
    }

    @Test
    void shouldReturnErrorForExistingLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login")).thenReturn(Optional.of(mockUser));
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("exists"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldValidatePasswordIsPresent() {
        when(mockRequest.getPassword()).thenReturn("password");
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("password", "password", "Password");
        verify(mockInputStringValidator).validateIsPresent(record);
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
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorRecord.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    //TODO integration test ?
    @Test
    void shouldReturnNoErrorsForValidInput() {
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}