package core.services.actions.shared;

import core.domain.user.User;
import core.requests.shared.SignOutRequest;
import core.responses.CoreError;
import core.responses.shared.SignOutResponse;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.actions.shared.SignOutValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignOutServiceTest {

    @Mock private SignOutValidator mockValidator;
    @Mock private UserService mockUserService;
    @Mock private SignOutRequest mockRequest;
    @Mock private CoreError mockCoreError;
    @Mock private User mockUser;
    @Mock private MutableLong mockCurrentUserId;

    @InjectMocks private SignOutService service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        SignOutResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.findGuestWithOpenCart()).thenReturn(Optional.of(mockUser));
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        SignOutResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldUseExistingGuestIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.findGuestWithOpenCart()).thenReturn(Optional.of(mockUser));
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUser.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
        verify(mockUserService, times(0)).createUser(any(UserRecord.class));
    }

    @Test
    void shouldCreateNewGuestIfNotPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.findGuestWithOpenCart()).thenReturn(Optional.empty());
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUser.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
        verify(mockUserService).createUser(any(UserRecord.class));
    }

    @Test
    void shouldChangeUserId() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUser.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
    }

}
