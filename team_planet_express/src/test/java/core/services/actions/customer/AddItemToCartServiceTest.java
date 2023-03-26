package core.services.actions.customer;

import core.database.CartDatabase;
import core.database.CartItemDatabase;
import core.database.Database;
import core.database.ItemDatabase;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.AddItemToCartRequest;
import core.responses.CoreError;
import core.responses.customer.AddItemToCartResponse;
import core.services.validators.customer.AddItemToCartValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AddItemToCartServiceTest {

    //TODO test

    private final Database mockDatabase = mock(Database.class);
    private final AddItemToCartValidator mockValidator = mock(AddItemToCartValidator.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    private final AddItemToCartRequest mockRequest = mock(AddItemToCartRequest.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    private final CartDatabase mockCartDatabase = mock(CartDatabase.class);
    private final CartItem mockCartItem = mock(CartItem.class);
    private final Item mockItem = mock(Item.class);

    private final Cart mockCart = mock(Cart.class);
    private final Optional<Cart> mockOptionalCart = mock(Optional.class);
    private final Optional<CartItem> mockOptionalCartItem = mock(Optional.class);
    private final Optional<Item> mockOptionalItem = mock(Optional.class);
    private final AddItemToCartService service =
            new AddItemToCartService(mockDatabase, mockValidator, mockCurrentUserId);

    @Test
    void shouldAddItemAndDecreaseAvailableQuantity() {
        when(mockRequest.getItemName()).thenReturn("Slurm");
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        when(mockCartDatabase.findOpenCartForUserId(mockCart.getId())).thenReturn(mockOptionalCart);
        when(mockOptionalCart.get()).thenReturn(mockCart);

        when(mockCart.getId()).thenReturn(1L);
        when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(mockOptionalCartItem);
        when(mockOptionalCartItem.get()).thenReturn(mockCartItem);
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockItemDatabase.findByName("Slurm")).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItem.getId()).thenReturn(1L);
        when(mockOptionalCart.get()).thenReturn(mockCart);
        when(mockOptionalCartItem.isEmpty()).thenReturn(true);
        AddItemToCartResponse response = service.execute(mockRequest);
        verify(mockCartItemDatabase).save(new CartItem(1L, 1L, 10));
        verify(mockItemDatabase).changeAvailableQuantity(1L, 10);
    }
}
