package shop.core.services.validators.actions.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Database;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.CurrentUserId;
import shop.matchers.InputStringValidatorDataMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private CurrentUserIdValidator mockCurrentUserIdValidator;
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
        verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateLoginNameIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("login name", "login", "Login name")));
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
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("password", "password", "Password")));
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
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorData.class))).thenReturn(Optional.of(mockCoreError));
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
