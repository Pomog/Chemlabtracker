package core.services.actions.guest;

import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.responses.guest.SignUpResponse;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.guest.SignUpValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class SignUpServiceTest {

    private final SignUpValidator mockValidator = mock(SignUpValidator.class);
    private final UserService mockUserService = mock(UserService.class);
    private final SignUpRequest mockRequest = mock(SignUpRequest.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final User mockUser = mock(User.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    private final SignUpService service = new SignUpService(mockValidator, mockUserService);

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
