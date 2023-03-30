package console_ui.actions.guest;

import console_ui.UserCommunication;
import core.domain.user.User;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.responses.guest.SignUpResponse;
import core.services.actions.guest.SignUpService;
import core.support.MutableLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SignUpUIActionTest {

    private final SignUpService mockSignUpService = mock(SignUpService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final SignUpResponse mockSignUpResponse = mock(SignUpResponse.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final User mockUser = mock(User.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    private final SignUpUIAction action =
            new SignUpUIAction(mockSignUpService, mockCurrentUserId,mockUserCommunication);

    @BeforeEach
    void setupMockResponse() {
        when(mockSignUpService.execute(any(SignUpRequest.class)))
                .thenReturn(mockSignUpResponse);
    }

    @Test
    void shouldPrintThreeInputPrompts() {
        when(mockSignUpResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockUserCommunication, times(3)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        when(mockUserCommunication.getInput()).thenReturn("name", "login", "password");
        when(mockSignUpResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockSignUpService).execute(new SignUpRequest(mockCurrentUserId, "name", "login", "password"));
    }

    @Test
    void shouldPrintSuccessMessage() {
        when(mockSignUpResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockSignUpResponse.hasErrors()).thenReturn(true);
        when(mockSignUpResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
