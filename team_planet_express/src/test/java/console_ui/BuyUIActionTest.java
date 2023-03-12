package console_ui;

import cart.Cart;
import cart.CartStatus;
import database.CartDatabase;
import org.junit.jupiter.api.Test;
import user_input.UserCommunication;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

class BuyUIActionTest {

    private final CartDatabase mockCartDatabase = mock(CartDatabase.class);
    private final UserCommunication mockUserCommunication = mock(UserCommunication.class);
    private final Cart mockCart = mock(Cart.class);

    private final BuyUIAction action = new BuyUIAction(mockCartDatabase, mockUserCommunication);

    @Test
    void shouldCloseCart() {
        when(mockCartDatabase.getAllCarts()).thenReturn(List.of(mockCart));
        when(mockCart.getCartStatus()).thenReturn(CartStatus.OPEN);
        when(mockCart.getId()).thenReturn(1L);
        action.execute();
        verify(mockCartDatabase).changeCartStatus(1L, CartStatus.CLOSED);
        verify(mockCartDatabase).changeLastActionDate(1L, LocalDate.now());
    }

}
