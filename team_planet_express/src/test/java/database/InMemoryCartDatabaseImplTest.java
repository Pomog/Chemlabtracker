package database;

import domain.cart.Cart;
import domain.cart.CartStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class InMemoryCartDatabaseImplTest {

    private final Cart mockCart = mock(Cart.class);

    private final InMemoryCartDatabaseImpl database = new InMemoryCartDatabaseImpl();

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockCart);
        assertEquals(1, database.getCarts().size());
    }

    @Test
    void shouldReturnFoundOpenCart() {
        when(mockCart.getUserId()).thenReturn(1L);
        when(mockCart.getCartStatus()).thenReturn(CartStatus.OPEN);
        database.getCarts().add(mockCart);
        assertTrue(database.findOpenCartForUserId(1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoOpenCarts() {
        when(mockCart.getUserId()).thenReturn(1L);
        when(mockCart.getCartStatus()).thenReturn(CartStatus.CLOSED);
        database.getCarts().add(mockCart);
        assertTrue(database.findOpenCartForUserId(1L).isEmpty());
    }

    @Test
    void shouldChangeCartStatus() {
        when(mockCart.getId()).thenReturn(1L);
        database.getCarts().add(mockCart);
        database.changeCartStatus(1L, CartStatus.CLOSED);
        verify(mockCart).setCartStatus(CartStatus.CLOSED);
    }

    @Test
    void shouldChangeLastActionDate() {
        when(mockCart.getId()).thenReturn(1L);
        database.getCarts().add(mockCart);
        database.changeLastActionDate(1L, LocalDate.now());
        verify(mockCart).setLastActionDate(LocalDate.now());
    }

    @Test
    void shouldReturn3Carts() {
        database.getCarts().add(mockCart);
        database.getCarts().add(mockCart);
        database.getCarts().add(mockCart);
        assertEquals(3, database.getAllCarts().size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockCart);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

}
