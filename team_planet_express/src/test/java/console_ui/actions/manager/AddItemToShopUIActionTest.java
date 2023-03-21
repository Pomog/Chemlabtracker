package console_ui.actions.manager;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import services.actions.manager.AddItemToShopService;
import services.exception.InvalidInputException;
import services.exception.ItemAlreadyExistsException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddItemToShopUIActionTest {

    private final AddItemToShopService mockAddItemToShopService = mock(AddItemToShopService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final AddItemToShopUIAction action =
            new AddItemToShopUIAction(mockAddItemToShopService, mockUserCommunication);

    @Test
    void shouldPrintThreeInputPrompts() {
        action.execute();
        verify(mockUserCommunication, times(3)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        String inputItem = "item";
        String inputPrice = "1.00";
        String inputQuantity = "1";
        when(mockUserCommunication.getInput()).thenReturn(inputItem, inputPrice, inputQuantity);
        action.execute();
        verify(mockAddItemToShopService).execute(inputItem, inputPrice, inputQuantity);
    }

    @Test
    void shouldPrintSuccessMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintInvalidInputErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new InvalidInputException(exceptionMessage, null))
                .when(mockAddItemToShopService).execute(null, null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintItemAlreadyExistsErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new ItemAlreadyExistsException(exceptionMessage))
                .when(mockAddItemToShopService).execute(null, null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
