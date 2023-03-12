package console_ui;

import cart_item.CartItem;
import eln.database.CartItemDatabase;
import eln.database.ItemDatabase;
import item.Item;
import org.junit.jupiter.api.Test;
import user_input.UserCommunication;

import java.util.List;

import static org.mockito.Mockito.*;

// TODO MOAR
class AddItemToCartUIActionTest {

    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final Item mockItem = mock(Item.class);

    private final AddItemToCartUIAction action =
            new AddItemToCartUIAction(mockItemDatabase, mockCartItemDatabase, mockUserCommunication);

    @Test
    void shouldAddItemAndDecreaseAvailableQuantity() {
        when(mockUserCommunication.getItem()).thenReturn("Slurm");
        when(mockItemDatabase.getAllItems()).thenReturn(List.of(mockItem));
        when(mockUserCommunication.getQuantity()).thenReturn(10);
        when(mockItem.getName()).thenReturn("Slurm");
        when(mockItem.getAvailableQuantity()).thenReturn(20);
        when(mockItem.getId()).thenReturn(1L);
        action.execute();
        verify(mockCartItemDatabase).save(new CartItem(mockItem, 10));
        verify(mockItemDatabase).changeAvailableQuantity(1L, 10);
    }

}
