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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignInServiceTest {

    @Mock private Database mockDatabase;
    @Mock private SignInValidator mockValidator;
    @Mock private SignInRequest mockRequest;
    @Mock private CoreError mockCoreError;
    @Mock private UserDatabase mockUserDatabase;
    @Mock private User mockUser;
    @Mock private MutableLong mockCurrentUserId;

    @InjectMocks private SignInService service;

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
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        SignInResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldGetUserFromDatabase() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
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
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
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
