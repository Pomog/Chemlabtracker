package console_ui;

import cart_item.CartItem;
import database.CartItemDatabase;
import database.ItemDatabase;
import item.Item;
import org.junit.jupiter.api.Test;
import user_input.UserCommunication;

import java.util.List;

import static org.mockito.Mockito.*;

class RemoveItemFromCartUIActionTest {

    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final Item mockItem = mock(Item.class);
    private final CartItem mockCartItem = mock(CartItem.class);

    private final RemoveItemFromCartUIAction action =
            new RemoveItemFromCartUIAction(mockItemDatabase, mockCartItemDatabase, mockUserCommunication);

    @Test
    void shouldAddItemAndDecreaseAvailableQuantity() {
        when(mockUserCommunication.getItem()).thenReturn("Slurm");
        when(mockCartItemDatabase.getAllCartItems()).thenReturn(List.of(mockCartItem));
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockItem.getName()).thenReturn("Slurm");
        when(mockItemDatabase.getAllItems()).thenReturn(List.of(mockItem));
        when(mockItem.getAvailableQuantity()).thenReturn(5);
        when(mockCartItem.getOrderedQuantity()).thenReturn(3);
        when(mockCartItem.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(1L);
        action.execute();
        verify(mockCartItemDatabase).deleteByID(1L);
        verify(mockItemDatabase).changeAvailableQuantity(1L, 8);
    }

}
