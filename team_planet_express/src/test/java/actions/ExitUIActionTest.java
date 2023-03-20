package actions;

import console_ui.UserCommunication;
import console_ui.actions.ExitUIAction;
import org.junit.jupiter.api.Test;
import services.actions.ExitService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ExitUIActionTest {

    private final ExitService mockExitService = mock(ExitService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final ExitUIAction action =
            new ExitUIAction(mockExitService, mockUserCommunication);

    @Test
    void shouldPrintExitMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockExitService).execute();
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
