package console_ui.actions.shop;

import console_ui.UserCommunication;
import domain.cart_item.CartItem;
import org.junit.jupiter.api.Test;
import services.actions.shop.ListCartItemsService;
import services.exception.NoOpenCartException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ListCartItemsUIActionTest {

    private final ListCartItemsService mockListCartItemsService = mock(ListCartItemsService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final CartItem mockCartItem = mock(CartItem.class);

    private final ListCartItemsUIAction action =
            new ListCartItemsUIAction(mockListCartItemsService, mockUserCommunication);

    @Test
    void shouldPrintHeaderAndCartEmptyMessage() {
        action.execute();
        verify(mockUserCommunication, times(2)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockListCartItemsService).execute();
    }

    @Test
    void shouldListHeaderAnd2CartItemsWithTotal() {
        when(mockListCartItemsService.execute()).thenReturn(List.of(mockCartItem, mockCartItem));
        action.execute();
        verify(mockUserCommunication, times(4)).informUser(anyString());
    }

    @Test
    void shouldPrintNoOpenCartErrorMessage() {
        String exceptionMessage = "exception message";
        doThrow(new NoOpenCartException(exceptionMessage))
                .when(mockListCartItemsService).execute();
        action.execute();
        verify(mockUserCommunication).informUser(exceptionMessage);
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
