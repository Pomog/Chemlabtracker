package shop.core.services.actions.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.domain.user.User;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.shared.SignOutValidator;
import shop.core.support.CurrentUserId;

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
    @Mock private CurrentUserId mockCurrentUserId;

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
