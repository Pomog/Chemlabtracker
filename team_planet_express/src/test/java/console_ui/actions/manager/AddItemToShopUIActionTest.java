package console_ui.actions.manager;

import console_ui.UserCommunication;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.responses.manager.AddItemToShopResponse;
import core.services.actions.manager.AddItemToShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
