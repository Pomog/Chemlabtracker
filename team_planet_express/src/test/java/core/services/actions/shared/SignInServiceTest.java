package core.services.actions.shared;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.shared.SignInValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SignInServiceTest {

    private final Database mockDatabase = mock(Database.class);
    private final SignInValidator mockValidator = mock(SignInValidator.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final SignInRequest mockRequest = mock(SignInRequest.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final User mockUser = mock(User.class);

    private final SignInService service = new SignInService(mockDatabase, mockValidator, mockCurrentUserId);

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        SignInResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        SignInResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldGetUserFromDatabase() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        service.execute(mockRequest);
        verify(mockUserDatabase).findByLogin("login");
    }

    @Test
    void shouldUpdateCurrentUserId() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.of(mockUser));
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(any(Long.class));
    }

    @Test
    void shouldThrowExceptionForMissingOptional() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.findByLogin("login")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> service.execute(mockRequest));
    }

}
