package core.services.validators.cart;

import core.database.CartDatabase;
import core.database.Database;
import core.domain.cart.Cart;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartValidatorTest {

    @Mock private Database mockDatabase;
    @Mock private CartDatabase mockCartDatabase;
    @Mock private Cart mockCart;

    @InjectMocks private CartValidator validator;

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
