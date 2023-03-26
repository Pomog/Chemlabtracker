package console_ui.actions.manager;

import console_ui.UserCommunication;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import core.responses.manager.ChangeItemDataResponse;
import core.services.actions.manager.ChangeItemDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ChangeItemDataUIActionTest {

    private final ChangeItemDataService mockChangeItemDataService = mock(ChangeItemDataService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final ChangeItemDataResponse mockChangeItemDataResponse = mock(ChangeItemDataResponse.class);
    private final CoreError mockCoreError = mock(CoreError.class);

    private final ChangeItemDataUIAction action =
            new ChangeItemDataUIAction(mockChangeItemDataService, mockUserCommunication);

    @BeforeEach
    void setupMockResponse() {
        when(mockChangeItemDataService.execute(any(ChangeItemDataRequest.class)))
                .thenReturn(mockChangeItemDataResponse);
    }

    @Test
    void shouldPrintFourInputPrompts() {
        action.execute();
        verify(mockUserCommunication, times(4)).requestInput(anyString());
    }

    @Test
    void shouldCallService() {
        when(mockUserCommunication.getInput()).thenReturn("1", "new name", "100.10", "10");
        action.execute();
        verify(mockChangeItemDataService)
                .execute(new ChangeItemDataRequest("1", "new name", "100.10", "10"));
    }

    @Test
    void shouldPrintTwoMessages() {
        action.execute();
        verify(mockUserCommunication, times(2)).informUser(anyString());
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockChangeItemDataResponse.hasErrors()).thenReturn(true);
        when(mockChangeItemDataResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
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
