package shop.console_ui.actions.guest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.domain.user.User;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpUIActionTest {

    @Mock private SignUpService mockSignUpService;
    @Mock private CurrentUserId mockCurrentUserId;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private SignUpResponse mockSignUpResponse;
    @Mock private User mockUser;
    @Mock private CoreError mockCoreError;

    @InjectMocks private SignUpUIAction action;

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

}
