package core.services.validators.actions.shared;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.services.validators.universal.system.DatabaseAccessValidator;
import core.services.validators.universal.system.MutableLongUserIdValidator;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;
import core.support.CurrentUserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private MutableLongUserIdValidator mockMutableLongUserIdValidator;
    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock private SignInRequest mockRequest;
    @Mock private CurrentUserId mockUserId;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private User mockUser;
    @Mock private CoreError mockCoreError;

    @InjectMocks private SignInValidator validator;

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        verify(mockMutableLongUserIdValidator).validateMutableLongUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateLoginNameIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("login name", "login", "Login name");
        verify(mockInputStringValidator).validateIsPresent(record);
    }

    @Test
    void shouldReturnErrorForNonexistentLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.empty());
        List<CoreError> errors = validator.validate(mockRequest);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("login"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("does not exist"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

    @Test
    void shouldValidatePasswordIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("password", "password", "Password");
        verify(mockInputStringValidator).validateIsPresent(record);
    }

    @Test
    void shouldReturnErrorForWrongPassword() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("wrong password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
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
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorRecord.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("password");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
