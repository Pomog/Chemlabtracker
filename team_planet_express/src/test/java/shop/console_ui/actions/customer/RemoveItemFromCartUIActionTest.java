package shop.console_ui.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.RemoveItemFromCartService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveItemFromCartUIActionTest {

    @Mock
    private RemoveItemFromCartService mockRemoveItemFromCartService;
    @Mock
    private UserCommunication mockUserCommunication;
    @Mock
    private RemoveItemFromCartResponse mockRemoveItemFromCartResponse;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private RemoveItemFromCartUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockRemoveItemFromCartService.execute(any(RemoveItemFromCartRequest.class)))
                .thenReturn(mockRemoveItemFromCartResponse);
    }

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(1)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockRemoveItemFromCartService).execute(any(RemoveItemFromCartRequest.class));
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockRemoveItemFromCartResponse.hasErrors()).thenReturn(true);
        when(mockRemoveItemFromCartResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }
}
