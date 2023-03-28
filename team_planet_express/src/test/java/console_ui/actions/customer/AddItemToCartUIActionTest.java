package console_ui.actions.customer;

import console_ui.UserCommunication;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.responses.customer.AddItemToCartResponse;
import core.services.actions.customer.AddItemToCartService;
import core.support.MutableLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddItemToCartUIActionTest {

    private final AddItemToCartService mockAddItemToCartService = mock(AddItemToCartService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final AddItemToCartResponse mockAddItemToCartResponse = mock(AddItemToCartResponse.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final AddItemToCartUIAction action =
            new AddItemToCartUIAction(mockAddItemToCartService, mockUserCommunication);

    @BeforeEach
    void setupSharedMockBehaviour() {
        when(mockAddItemToCartService.getCurrentUserId()).thenReturn(mockCurrentUserId);
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

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
