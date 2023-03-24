package console_ui.actions.manager;

import console_ui.UserCommunication;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.responses.manager.AddItemToShopResponse;
import core.services.actions.manager.AddItemToShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddItemToShopUIActionTest {

    private final AddItemToShopService mockAddItemToShopService = mock(AddItemToShopService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final AddItemToShopResponse mockAddItemToShopResponse = mock(AddItemToShopResponse.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final AddItemToShopUIAction action =
            new AddItemToShopUIAction(mockAddItemToShopService, mockUserCommunication);

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
        when(mockAddItemToShopResponse.hasErrors()).thenReturn(false);
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

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
