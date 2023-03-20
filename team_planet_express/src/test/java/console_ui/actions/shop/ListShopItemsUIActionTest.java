package console_ui.actions.shop;

import console_ui.UserCommunication;
import domain.item.Item;
import org.junit.jupiter.api.Test;
import services.actions.shop.ListShopItemsService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ListShopItemsUIActionTest {

    private final ListShopItemsService mockListShopItemsService = mock(ListShopItemsService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final Item mockItem = mock(Item.class);

    private final ListShopItemsUIAction action =
            new ListShopItemsUIAction(mockListShopItemsService, mockUserCommunication);

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(1)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockListShopItemsService).execute();
    }

    @Test
    void shouldListHeaderAndTwoItems() {
        when(mockListShopItemsService.execute()).thenReturn(List.of(mockItem, mockItem));
        action.execute();
        verify(mockUserCommunication, times(3)).informUser(anyString());
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
