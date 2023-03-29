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

    //TODO I moved CartValidator into AddItemToCartValidator, so this thing can be simplified a lot

    private final Database mockDatabase = mock(Database.class);
    private final AddItemToCartValidator mockValidator = mock(AddItemToCartValidator.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);
    //TODO Insert CartValidator mock here

    private final AddItemToCartRequest mockRequest = mock(AddItemToCartRequest.class);
    // never used
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

    // If you have "and" in a name, it means that this thing is bad and breaks SRP
    // Also it leads to a wall of incomprehensible when-s, which are hard to understand and keep track of
    // I didn't want to make Service constructor ugly, so we ended up with an unmockable CartValidator..
    // As a result this makes calls to real CartValidator with mockDatabase
    // Not having a mockable CartValidator also add to the incomprehensible wall of when-s though..
    // Also, also debugger FTW
    // Debugger clearly shows, that very first error check fails, so there was no way to get to database.save()
    @Test
    void shouldAddItemAndDecreaseAvailableQuantity() {
        when(mockRequest.getItemName()).thenReturn("Slurm");
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockCurrentUserId.getValue()).thenReturn(1L);
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockDatabase.accessCartDatabase()).thenReturn(mockCartDatabase);
        // I guess mock doesn't understand this way of passing an id in this case
        // Probably it does not call anything from here and just expects a call with mockCart.getId() as a parameter
        //when(mockCartDatabase.findOpenCartForUserId(mockCart.getId())).thenReturn(mockOptionalCart);
        when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(mockOptionalCart);
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
        // new quantity is -10 without when() for getAvailableQuantity
        verify(mockItemDatabase).changeAvailableQuantity(1L, -10);
        //verify(mockItemDatabase).changeAvailableQuantity(1L, 10);

        // As far as I understand, making mockOptional-s is not necessary
        // You can use
        //when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        // instead of
        //when(mockCartDatabase.findOpenCartForUserId(1L)).thenReturn(mockOptionalCart);
        //when(mockOptionalCartItem.isEmpty()).thenReturn(false);
        // or
        //when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        // instead of
        //when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(mockOptionalCartItem);
        //when(mockOptionalCartItem.isEmpty()).thenReturn(true);

    }

    // I would split the tests, so that each one would be responsible for one thing only
    @Test
    void shouldAddNewItemToCart() {
    }

    @Test
    void shouldDecreaseAvailableQuantity() {
    }

}
