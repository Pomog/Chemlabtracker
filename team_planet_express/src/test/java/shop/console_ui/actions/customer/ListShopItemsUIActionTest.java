package shop.console_ui.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.domain.item.Item;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListShopItemsUIActionTest {

    @Mock private ListShopItemsService mockListShopItemsService;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private ListShopItemsResponse mockListShopItemsResponse;
    @Mock private Item mockItem;

    @InjectMocks private ListShopItemsUIAction action;

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

}
