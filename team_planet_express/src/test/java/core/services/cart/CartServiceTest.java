package core.services.cart;

import core.database.CartItemDatabase;
import core.database.Database;
import core.database.ItemDatabase;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.services.exception.ServiceMissingDataException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartServiceTest {

    private final Database mockDatabase = mock(Database.class);
    private final CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    private final ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    private final CartItem mockCartItem = mock(CartItem.class);
    private final Item mockItem = mock(Item.class);

    private final CartService service = new CartService(mockDatabase);

    @Test
    void shouldReturnSum() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockCartItemDatabase.getAllCartItemsForCartId(1L)).thenReturn(List.of(mockCartItem, mockCartItem, mockCartItem));
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(anyLong())).thenReturn(Optional.of(mockItem));
        when(mockItem.getPrice()).thenReturn(new BigDecimal("10"), new BigDecimal("7.52"), new BigDecimal("0.27"));
        when(mockCartItem.getOrderedQuantity()).thenReturn(1, 3, 7);
        BigDecimal actualResult = service.getSum(1L);
        assertEquals(new BigDecimal("34.45"), actualResult);
    }

    @Test
    void shouldThrowExceptionForMissingOptional() {
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockCartItemDatabase.getAllCartItemsForCartId(1L)).thenReturn(List.of(mockCartItem, mockCartItem, mockCartItem));
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItemDatabase.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> service.getSum(1L));
    }

}
