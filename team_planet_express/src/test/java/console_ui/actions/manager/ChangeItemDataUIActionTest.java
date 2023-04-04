package console_ui.actions.manager;

import console_ui.UserCommunication;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import core.responses.manager.ChangeItemDataResponse;
import core.services.actions.manager.ChangeItemDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeItemDataUIActionTest {

    @Mock private ChangeItemDataService mockChangeItemDataService;
    @Mock private UserCommunication mockUserCommunication;
    @Mock private ChangeItemDataResponse mockChangeItemDataResponse;
    @Mock private CoreError mockCoreError;

    @InjectMocks private ChangeItemDataUIAction action;

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

}
