package console_ui.actions.customer;

import console_ui.UserCommunication;
import org.junit.jupiter.api.Test;
import core.services.actions.customer.BuyService;
import core.services.exception.CartIsEmptyException;
import core.services.exception.NoOpenCartException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class BuyUIActionTest {

//    private final BuyService mockBuyService = mock(BuyService.class);
//    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
//
//    private final BuyUIAction action =
//            new BuyUIAction(mockBuyService, mockUserCommunication);
//
//    @Test
//    void shouldCallService() {
//        action.execute();
//        verify(mockBuyService).execute();
//    }
//
//    @Test
//    void shouldPrintSuccessMessage() {
//        action.execute();
//        verify(mockUserCommunication).informUser(anyString());
//    }
//
//    @Test
//    void shouldPrintNoOpenCartErrorMessage() {
//        String exceptionMessage = "exception message";
//        doThrow(new NoOpenCartException(exceptionMessage)).when(mockBuyService).execute();
//        action.execute();
//        verify(mockUserCommunication).informUser(exceptionMessage);
//    }
//
//    @Test
//    void shouldPrintCartIsEmptyErrorMessage() {
//        String exceptionMessage = "exception message";
//        doThrow(new CartIsEmptyException(exceptionMessage)).when(mockBuyService).execute();
//        action.execute();
//        verify(mockUserCommunication).informUser(exceptionMessage);
//    }
//
//    @Test
//    void shouldReturnActionName() {
//        assertFalse(Objects.isNull(action.getActionName()) ||
//                action.getActionName().isBlank());
//    }

}
