package shop;

import data.Item;
import item_generator.HardcodedItemGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ShopTest {

    private final Shop shop = new Shop(new HardcodedItemGenerator());
    private final Item mockItem = mock(Item.class);

    @Test
    void shouldReturnTrueForAvailableItem() {
        assertTrue(shop.itemAvailable("Slurm"));
    }

    @Test
    void shouldReturnFalseForUnavailableItem() {
        assertFalse(shop.itemAvailable("Human Broth"));
    }

    @Test
    void shouldReturnFalseForZeroQuantityItem() {
        assertFalse(shop.itemAvailable("Popplers"));
    }

    @Test
    void shouldReturnTrueForExistingItem() {
        assertTrue(shop.findByName("Lightspeed Briefs").isPresent());
    }

    @Test
    void shouldReturnFalseNonexistentItem() {
        assertFalse(shop.findByName("Benderillos").isPresent());
    }

    @Test
    void shouldDecreaseItemAvailableQuantity() {
        Integer amount = 1;
        when(mockItem.getName()).thenReturn("Blank Robot");
        doNothing().when(mockItem).decreaseQuantityAvailable(amount);
        shop.decreaseItemQuantity(mockItem, amount);
        verify(mockItem).decreaseQuantityAvailable(amount);
    }

    @Test
    void shouldNotDecreaseItemAvailableQuantityForNonexistentItem() {
        Integer amount = 1;
        when(mockItem.getName()).thenReturn("Extreme Walrus Juice");
        doNothing().when(mockItem).decreaseQuantityAvailable(amount);
        shop.decreaseItemQuantity(mockItem, amount);
        verify(mockItem, never()).decreaseQuantityAvailable(amount);
    }

}
