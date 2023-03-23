package console_ui.actions.customer;

import console_ui.UserCommunication;
import core.domain.item.Item;
import core.requests.customer.ListShopItemsRequest;
import core.responses.customer.ListShopItemsResponse;
import core.services.actions.customer.ListShopItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ListShopItemsUIActionTest {

    private final ListShopItemsService mockListShopItemsService = mock(ListShopItemsService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final ListShopItemsResponse mockListShopItemsResponse = mock(ListShopItemsResponse.class);
    private final Item mockItem = mock(Item.class);

    private final ListShopItemsUIAction action =
            new ListShopItemsUIAction(mockListShopItemsService, mockUserCommunication);

    @BeforeEach
    void setupMockResponse() {
        when(mockListShopItemsService.execute(any(ListShopItemsRequest.class)))
                .thenReturn(mockListShopItemsResponse);
    }

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(1)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockListShopItemsService).execute(any(ListShopItemsRequest.class));
    }

    @Test
    void shouldListHeaderAndTwoItems() {
        when(mockListShopItemsResponse.getShopItems()).thenReturn(List.of(mockItem, mockItem));
        action.execute();
        verify(mockUserCommunication, times(3)).informUser(anyString());
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
