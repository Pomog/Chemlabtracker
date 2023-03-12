package console_ui;

import database.ItemDatabase;
import item.Item;
import org.junit.jupiter.api.Test;
import user_input.UserCommunication;

import java.util.List;

import static org.mockito.Mockito.*;

class ListShopItemsUIActionTest {

    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final Item mockItem = mock(Item.class);

    private final ListShopItemsUIAction action =
            new ListShopItemsUIAction(mockItemDatabase, mockUserCommunication);

    @Test
    void shouldListHeaderAnd1Item() {
        when(mockItemDatabase.getAllItems()).thenReturn(List.of(mockItem));
        action.execute();
        verify(mockUserCommunication, times(2)).informUser(anyString());
    }

}
