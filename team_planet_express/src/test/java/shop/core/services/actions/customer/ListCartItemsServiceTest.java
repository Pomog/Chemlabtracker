package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemDatabase;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCartItemsServiceTest {

    @Mock
    private Database mockDatabase;
    @Mock
    private ListCartItemValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private ListCartItemsRequest mockRequest;
    @Mock
    private CartItemDatabase mockCartItemDatabase;
    @Mock
    private CartService mockCartService;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private ListCartItemsService service;

    @BeforeEach
    void initMock() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldReturnListCartItem() {

        when(mockCartItemDatabase.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        when(mockCart.getUserId()).thenReturn(1L);
        when(mockCartService.getSum(1L)).thenReturn(BigDecimal.valueOf(1));
        ListCartItemsResponse response = service.execute(mockRequest);

        assertEquals(response.getCartItems().size(), 2);
    }

    @Test
    void shouldReturnSum() {

        when(mockCartItemDatabase.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        when(mockCart.getUserId()).thenReturn(1L);
        when(mockCartService.getSum(1L)).thenReturn(BigDecimal.valueOf(1));
        ListCartItemsResponse response = service.execute(mockRequest);

        assertEquals(response.getCartTotal(), BigDecimal.valueOf(1));
    }
}
