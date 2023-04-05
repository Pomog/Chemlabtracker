package shop.core.services.actions.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.guest.SignUpValidator;
import shop.core.support.CurrentUserId;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

    @Mock private SignUpValidator mockValidator;
    @Mock private UserService mockUserService;
    @Mock private SignUpRequest mockRequest;
    @Mock private CoreError mockCoreError;
    @Mock private User mockUser;
    @Mock private CurrentUserId mockCurrentUserId;

    @InjectMocks private SignUpService service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        SignUpResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        SignUpResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldCalUserServiceToSaveValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getName()).thenReturn("name");
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        service.execute(mockRequest);
        UserRecord record = new UserRecord("name", "login", "password", UserRole.CUSTOMER);
        verify(mockUserService).createUser(record);
    }

    @Test
    void shouldUpdateCurrentUserId() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockUser.getId()).thenReturn(1L);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
    }

}
