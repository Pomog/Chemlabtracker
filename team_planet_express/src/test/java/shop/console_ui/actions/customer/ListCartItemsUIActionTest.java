package shop.console_ui.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListCartItemsUIActionTest {

    @Mock
    private ListCartItemsService mockListCartItemsService;
    @Mock
    private UserCommunication mockUserCommunication;
    @Mock
    private ListCartItemsResponse mockListCartItemsResponse;

    @InjectMocks
    private ListCartItemsUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockListCartItemsService.execute(any(ListCartItemsRequest.class)))
                .thenReturn(mockListCartItemsResponse);
    }

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(2)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockListCartItemsService).execute(any(ListCartItemsRequest.class));
    }

}
