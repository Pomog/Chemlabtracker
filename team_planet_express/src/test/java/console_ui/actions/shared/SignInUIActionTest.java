package console_ui.actions.shared;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import core.services.actions.shared.SignInService;
import core.services.exception.InvalidLoginNameException;
import core.services.exception.InvalidLoginPasswordException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SignInUIActionTest {

    private final SignInService mockSignInService = mock(SignInService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final SignInUIAction action =
            new SignInUIAction(mockSignInService, mockUserCommunication);

    @Test
    void shouldPrintTwoInputPrompts() {
        action.execute();
        verify(mockUserCommunication, times(2)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        String inputName = "name";
        String inputPassword = "password";
        when(mockUserCommunication.getInput()).thenReturn(inputName, inputPassword);
        action.execute();
        verify(mockSignInService).execute(inputName, inputPassword);
    }

    @Test
    void shouldPrintSuccessMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintInvalidLoginNameErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new InvalidLoginNameException(exceptionMessage))
                .when(mockSignInService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintInvalidLoginPasswordErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new InvalidLoginPasswordException(exceptionMessage))
                .when(mockSignInService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
