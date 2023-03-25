package console_ui.actions.shared;

import console_ui.UserCommunication;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.actions.shared.SignInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SignInUIActionTest {

    private final SignInService mockSignInService = mock(SignInService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final SignInResponse mockSignInResponse = mock(SignInResponse.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final User mockUser = mock(User.class);

    private final SignInUIAction action =
            new SignInUIAction(mockSignInService, mockUserCommunication);

    @BeforeEach
    void setupMockResponse() {
        when(mockSignInService.execute(any(SignInRequest.class)))
                .thenReturn(mockSignInResponse);
    }

    @Test
    void shouldPrintTwoInputPrompts() {
        when(mockSignInResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockUserCommunication, times(2)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        when(mockUserCommunication.getInput()).thenReturn("login", "password");
        when(mockSignInResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockSignInService).execute(new SignInRequest("login", "password"));
    }

    @Test
    void shouldPrintSuccessMessage() {
        when(mockSignInResponse.getUser()).thenReturn(mockUser);
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockSignInResponse.hasErrors()).thenReturn(true);
        when(mockSignInResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
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
