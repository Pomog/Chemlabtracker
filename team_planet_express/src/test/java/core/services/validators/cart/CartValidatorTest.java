package core.services.validators.cart;

import core.database.CartDatabase;
import core.database.Database;
import core.domain.cart.Cart;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartValidatorTest {

    private final Database mockDatabase = mock(Database.class);
    private final CartDatabase mockCartDatabase = mock(CartDatabase.class);
    private final Cart mockCart = mock(Cart.class);

    private final CartValidator validator = new CartValidator(mockDatabase);

    @Test
    void shouldReturnNoError() {
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        Optional<CoreError> error = validator.validateOpenCartExistsForUserId(1L);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnError() {
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        Optional<CoreError> error = validator.validateOpenCartExistsForUserId(1L);
        assertTrue(error.isPresent());
    }

}
