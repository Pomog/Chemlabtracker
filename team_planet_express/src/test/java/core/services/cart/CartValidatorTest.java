package core.services.cart;

import core.database.CartDatabase;
import core.domain.cart.Cart;
import core.services.exception.NoOpenCartException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartValidatorTest {

    private final CartDatabase mockCartDatabase = mock(CartDatabase.class);
    private final Cart mockCart = mock(Cart.class);

    private final CartValidator cartValidator = new CartValidator();

    @Test
    void shouldReturnOpenCart() {
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertEquals(mockCart, cartValidator.getOpenCartForUserId(mockCartDatabase, 1L));
    }

    @Test
    void shouldThrowNoOpenCartException() {
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertThrows(NoOpenCartException.class, () -> cartValidator.getOpenCartForUserId(mockCartDatabase, 1L));
    }

}
