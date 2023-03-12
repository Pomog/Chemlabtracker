package database;

import cart_item.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InMemoryCartItemDatabaseImplTest {

    private final CartItem mockCartItem = mock(CartItem.class);

    private final InMemoryCartItemDatabaseImpl database = new InMemoryCartItemDatabaseImpl();

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockCartItem);
        assertEquals(1, database.getCartItems().size());
    }

    @Test
    void shouldDecreaseInSizeAfterRemove() {
        when(mockCartItem.getId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        database.deleteByID(1L);
        assertEquals(0, database.getCartItems().size());
    }

    @Test
    void shouldChangeOrderedQuantity() {
        when(mockCartItem.getId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        database.changeOrderedQuantity(1L, 10);
        verify(mockCartItem).setOrderedQuantity(10);
    }

    @Test
    void shouldReturn4CartItems() {
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        assertEquals(4, database.getAllCartItems().size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockCartItem);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

}