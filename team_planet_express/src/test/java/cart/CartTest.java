package cart;

import data.Item;
import org.junit.jupiter.api.Test;
import shop.Shop;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartTest {

    private final Cart cart = new Cart();
    private final Shop mockShop = mock(Shop.class);

    @Test
    void shouldHaveSizeOfOneAfterAdd() {
        Item item = new Item("name", new BigDecimal("1.00"), 1);
        Integer amountOrdered = 1;
        doNothing().when(mockShop).decreaseItemQuantity(item, amountOrdered);
        cart.addItem(item, 1, mockShop);
        assertEquals(1, cart.getOrders().size());
    }

    @Test
    void shouldDecreaseAmountInTheShopAfterAdd() {
        Item item = new Item("name", new BigDecimal("1.00"), 1);
        Integer amountOrdered = 1;
        doNothing().when(mockShop).decreaseItemQuantity(item, amountOrdered);
        cart.addItem(item, 1, mockShop);
        verify(mockShop).decreaseItemQuantity(item, amountOrdered);
    }

}
