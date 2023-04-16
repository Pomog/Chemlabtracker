package shop.console_ui.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToCartUIActionTest {

    @Mock private AddItemToCartService mockAddItemToCartService;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private AddItemToCartResponse mockAddItemToCartResponse;
    @Mock private CoreError mockCoreError;

    @InjectMocks private AddItemToCartUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockAddItemToCartService.execute(any(AddItemToCartRequest.class)))
                .thenReturn(mockAddItemToCartResponse);
    }

    @Test
    void shouldPrintTwoInputPrompts() {
        when(mockAddItemToCartResponse.hasErrors()).thenReturn(false);
        action.execute();
        verify(mockUserCommunication, times(2)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        when(mockAddItemToCartResponse.hasErrors()).thenReturn(false);
        action.execute();
        verify(mockAddItemToCartService).execute(any(AddItemToCartRequest.class));
    }

    @Test
    void shouldPrintSuccessMessage() {
        when(mockAddItemToCartResponse.hasErrors()).thenReturn(false);
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockAddItemToCartResponse.hasErrors()).thenReturn(true);
        when(mockAddItemToCartResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }

}
