package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemDatabase;
import shop.core.database.Database;
import shop.core.database.ItemDatabase;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveItemFromCartServiceTest {

    @Mock
    private Database mockDatabase;
    @Mock
    private RemoveItemFromCartValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private RemoveItemFromCartRequest mockRequest;
    @Mock
    private CartItemDatabase mockCartItemDatabase;
    @Mock
    private ItemDatabase mockItemDatabase;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private RemoveItemFromCartService service;

    @BeforeEach
    void initMock() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldDeleteFromCart() {

        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(2L);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(1L, 2L)).thenReturn(mockCartItem);
        service.execute(mockRequest);

        verify(mockCartItemDatabase).deleteByID(3L);
    }

    @Test
    void shouldReturnAvailableQuantity() {

        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(2L);
        when(mockItem.getAvailableQuantity()).thenReturn(10);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockCartItem.getOrderedQuantity()).thenReturn(11);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(1L, 2L)).thenReturn(mockCartItem);
        service.execute(mockRequest);

        verify(mockItemDatabase).changeAvailableQuantity(2L, 21);

    }
}
