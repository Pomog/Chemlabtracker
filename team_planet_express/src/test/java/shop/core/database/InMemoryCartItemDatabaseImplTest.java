package shop.core.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.domain.cart_item.CartItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InMemoryCartItemDatabaseImplTest {

    @Mock private CartItem mockCartItem;

    @InjectMocks private InMemoryCartItemDatabaseImpl database;

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockCartItem);
        assertEquals(1, database.getCartItems().size());
    }

    @Test
    void shouldReturnFoundCartItem() {
        when(mockCartItem.getCartId()).thenReturn(1L);
        when(mockCartItem.getItemId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        assertTrue(database.findByCartIdAndItemId(1L, 1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentItem() {
        when(mockCartItem.getCartId()).thenReturn(1L);
        when(mockCartItem.getItemId()).thenReturn(2L);
        database.getCartItems().add(mockCartItem);
        assertTrue(database.findByCartIdAndItemId(1L, 1L).isEmpty());
    }

    @Test
    void shouldDecreaseInSizeAfterRemove() {
        when(mockCartItem.getId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        database.deleteByID(1L);
        assertEquals(0, database.getCartItems().size());
    }

    //testforfaildelete

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
    void shouldReturn2CartItems() {
        when(mockCartItem.getCartId()).thenReturn(1L, 2L, 1L, 2L);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        assertEquals(2, database.getAllCartItemsForCartId(1L).size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockCartItem);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

}