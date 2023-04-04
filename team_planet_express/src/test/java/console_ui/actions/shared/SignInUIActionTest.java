package console_ui.actions.shared;

import console_ui.UserCommunication;
import core.domain.user.User;
import core.requests.shared.SignInRequest;
import core.responses.CoreError;
import core.responses.shared.SignInResponse;
import core.services.actions.shared.SignInService;
import core.support.MutableLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInUIActionTest {

    @Mock private SignInService mockSignInService;
    @Mock private MutableLong mockCurrentUserId;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private SignInResponse mockSignInResponse;
    @Mock private User mockUser;
    @Mock private CoreError mockCoreError;

    @InjectMocks private SignInUIAction action;

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
        verify(mockSignInService).execute(new SignInRequest(mockCurrentUserId, "login", "password"));
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

}
