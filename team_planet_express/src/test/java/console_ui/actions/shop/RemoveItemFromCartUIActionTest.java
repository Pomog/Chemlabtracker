package console_ui.actions.shop;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import services.actions.shop.RemoveItemFromCartService;
import services.exception.ItemNotFoundException;
import services.exception.NoOpenCartException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class RemoveItemFromCartUIActionTest {

    private final RemoveItemFromCartService mockRemoveItemFromCartService = mock(RemoveItemFromCartService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final RemoveItemFromCartUIAction action =
            new RemoveItemFromCartUIAction(mockRemoveItemFromCartService, mockUserCommunication);

    @Test
    void shouldPrintInputPrompt() {
        action.execute();
        verify(mockUserCommunication).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        String inputItem = "item";
        when(mockUserCommunication.getInput()).thenReturn(inputItem);
        action.execute();
        verify(mockRemoveItemFromCartService).execute(inputItem);
    }

    @Test
    void shouldPrintSuccessMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintItemNotFoundErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new ItemNotFoundException(exceptionMessage))
                .when(mockRemoveItemFromCartService).execute(null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintNoOpenCartErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new NoOpenCartException(exceptionMessage))
                .when(mockRemoveItemFromCartService).execute(null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
