package shop.console_ui.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.actions.customer.BuyService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyUIActionTest {

    @Mock
    private BuyService mockBuyService;
    @Mock
    private UserCommunication mockUserCommunication;
    @Mock
    private BuyResponse mockBuyResponse;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private BuyUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockBuyService.execute(any(BuyRequest.class)))
                .thenReturn(mockBuyResponse);
    }

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(1)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockBuyService).execute(any(BuyRequest.class));
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockBuyResponse.hasErrors()).thenReturn(true);
        when(mockBuyResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }
}
