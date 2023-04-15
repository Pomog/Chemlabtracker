package shop.acceptance.manager;

import org.junit.jupiter.api.Test;
import shop.acceptance.AcceptanceTest;
import shop.core.domain.item.Item;
import shop.core.responses.manager.AddItemToShopResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemsToTheShopAcceptanceTest extends AcceptanceTest {

    @Test
    void shouldAddItemsToTheShop() {
        int shopItemCountBefore = getDatabase().accessItemDatabase().getAllItems().size();
        AddItemToShopResponse addItemToShopResponse = executeAddItemToShop("new item 1", "1.01", "5");
        assertFalse(addItemToShopResponse.hasErrors());
        addItemToShopResponse = executeAddItemToShop("new item 2", "7.07", "3");
        assertFalse(addItemToShopResponse.hasErrors());
        List<Item> shopItems = getDatabase().accessItemDatabase().getAllItems();
        assertEquals(2, shopItems.size() - shopItemCountBefore);
        assertTrue(getDatabase().accessItemDatabase().findByName("new item 1").isPresent());
        Item newItem1 = getDatabase().accessItemDatabase().findByName("new item 1").get();
        assertEquals(new BigDecimal("1.01"), newItem1.getPrice());
        assertEquals(5, newItem1.getAvailableQuantity());
        assertTrue(getDatabase().accessItemDatabase().findByName("new item 2").isPresent());
        Item newItem2 = getDatabase().accessItemDatabase().findByName("new item 2").get();
        assertEquals(new BigDecimal("7.07"), newItem2.getPrice());
        assertEquals(3, newItem2.getAvailableQuantity());
    }

}
