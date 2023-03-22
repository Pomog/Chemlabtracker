package console_ui.actions.customer;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import core.services.actions.customer.AddItemToCartService;
import core.services.exception.InvalidInputException;
import core.services.exception.InvalidQuantityException;
import core.services.exception.ItemNotFoundException;
import core.services.exception.NoOpenCartException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddItemToCartUIActionTest {

    private final AddItemToCartService mockAddItemToCartService = mock(AddItemToCartService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final AddItemToCartUIAction action =
            new AddItemToCartUIAction(mockAddItemToCartService, mockUserCommunication);

    @Test
    void shouldPrintTwoInputPrompts() {
        action.execute();
        verify(mockUserCommunication, times(2)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        String inputItem = "item";
        String inputQuantity = "1";
        when(mockUserCommunication.getInput()).thenReturn(inputItem, inputQuantity);
        action.execute();
        verify(mockAddItemToCartService).execute(inputItem, inputQuantity);
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
                .when(mockAddItemToCartService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintItemNotFoundErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new ItemNotFoundException(exceptionMessage))
                .when(mockAddItemToCartService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintInvalidQuantityErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new InvalidQuantityException(exceptionMessage))
                .when(mockAddItemToCartService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldPrintNoOpenCartErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new NoOpenCartException(exceptionMessage))
                .when(mockAddItemToCartService).execute(null, null);
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
