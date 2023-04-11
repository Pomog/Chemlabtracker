package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartDatabase;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyServiceTest {

    @Mock
    private Database mockDatabase;
    @Mock
    private BuyValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private BuyRequest mockRequest;
    @Mock
    private CartDatabase mockCartDatabase;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private BuyService service;

    @Test
    void shouldCloseCart() {
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        service.execute(mockRequest);

        verify(mockCartDatabase).changeCartStatus(1L, CartStatus.CLOSED);
        verify(mockCartDatabase).changeLastActionDate(1L, LocalDate.now());
    }

}
