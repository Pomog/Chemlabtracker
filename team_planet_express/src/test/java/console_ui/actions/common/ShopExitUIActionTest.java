package console_ui.actions.common;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import services.actions.common.ShopExitService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ShopExitUIActionTest {

    private final ShopExitService mockShopExitService = mock(ShopExitService.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);

    private final ShopExitUIAction action =
            new ShopExitUIAction(mockShopExitService, mockUserCommunication);

    @Test
    void shouldPrintExitMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockShopExitService).execute();
    }

    @Test
    void shouldReturnActionName() {
        assertFalse(Objects.isNull(action.getActionName()) ||
                action.getActionName().isBlank());
    }

}
