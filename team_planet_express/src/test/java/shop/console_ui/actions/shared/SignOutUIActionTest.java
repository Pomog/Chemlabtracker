package shop.console_ui.actions.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignOutService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SignOutUIActionTest {


    @Mock
    private SignOutService mockSignOutService;
    @Mock
    private UserCommunication mockUserCommunication;
    @Mock
    private SignOutResponse mockSignOutResponse;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private SignOutUIAction action;

    @BeforeEach
    void setupMockResponse() {
        when(mockSignOutService.execute(any(SignOutRequest.class)))
                .thenReturn(mockSignOutResponse);
    }

    @Test
    void shouldPrintHeader() {
        action.execute();
        verify(mockUserCommunication, times(1)).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockSignOutService).execute(any(SignOutRequest.class));
    }

    @Test
    void shouldPrintErrorMessages() {
        when(mockSignOutResponse.hasErrors()).thenReturn(true);
        when(mockSignOutResponse.getErrors()).thenReturn(List.of(mockCoreError, mockCoreError));
        when(mockCoreError.getMessage()).thenReturn("message");
        action.execute();
        verify(mockUserCommunication, times(2)).informUser("message");
    }

}
