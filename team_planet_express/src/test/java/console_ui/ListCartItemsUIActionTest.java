package console_ui;

import cart_item.CartItem;
import database.CartItemDatabase;
import org.junit.jupiter.api.Test;
import user_input.UserCommunication;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ListCartItemsUIActionTest {

    private final CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final CartItem mockCartItem = mock(CartItem.class);

    private final ListCartItemsUIAction action =
            new ListCartItemsUIAction(mockCartItemDatabase, mockUserCommunication);

    @Test
    void shouldListHeaderAnd2CartItems() {
        when(mockCartItemDatabase.getAllCartItems()).thenReturn(List.of(mockCartItem, mockCartItem));
        action.execute();
        verify(mockUserCommunication, times(3)).informUser(anyString());
    }

    @Test
    void shouldListHeaderAndEmptyCartMessage() {
        when(mockCartItemDatabase.getAllCartItems()).thenReturn(new ArrayList<>());
        action.execute();
        verify(mockUserCommunication, times(2)).informUser(anyString());
    }

}
