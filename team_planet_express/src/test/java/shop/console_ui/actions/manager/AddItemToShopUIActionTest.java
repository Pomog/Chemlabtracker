package shop.console_ui.actions.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.actions.manager.AddItemToShopService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToShopUIActionTest {

    @Mock private AddItemToShopService mockAddItemToShopService;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private AddItemToShopResponse mockAddItemToShopResponse;
    @Mock private CoreError mockCoreError;

    @InjectMocks private AddItemToShopUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockAddItemToShopService.execute(any(AddItemToShopRequest.class)))
                .thenReturn(mockAddItemToShopResponse);
    }

    @Test
    void shouldPrintThreeInputPrompts() {
        action.execute();
        verify(mockUserCommunication, times(3)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        when(mockUserCommunication.getInput()).thenReturn("name", "100.10", "10");
        action.execute();
        verify(mockAddItemToShopService).execute(new AddItemToShopRequest("name", "100.10", "10"));
    }

    @Test
    void shouldPrintSuccessMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockAddItemToShopResponse.hasErrors()).thenReturn(true);
        when(mockAddItemToShopResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }

}
