package core.services.actions.shared;

import core.domain.user.User;
import core.requests.shared.SignOutRequest;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.actions.shared.SignOutValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class SignOutServiceTest {

    private final SignOutValidator mockValidator = mock(SignOutValidator.class);
    private final UserService mockUserService = mock(UserService.class);
    private final SignOutRequest mockRequest = mock(SignOutRequest.class);
    private final User mockUser = mock(User.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    private final SignOutService service = new SignOutService(mockValidator, mockUserService);

    @Test
    void shouldUseExistingGuestIfPresent() {
        when(mockUserService.findGuestWithOpenCart()).thenReturn(Optional.of(mockUser));
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUser.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
        verify(mockUserService, times(0)).createUser(any(UserRecord.class));
    }

    @Test
    void shouldCreateNewGuestIfNotPresent() {
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
        when(mockUserService.createUser(any(UserRecord.class))).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUser.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(1L);
    }

    //TODO test validator

}
