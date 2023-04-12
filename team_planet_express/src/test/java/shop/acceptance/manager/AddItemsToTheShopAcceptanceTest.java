package shop.acceptance.manager;

import org.junit.jupiter.api.Test;
import shop.ApplicationContext;
import shop.acceptance.ApplicationContextSetup;
import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.services.actions.manager.AddItemToShopService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddItemsToTheShopAcceptanceTest {

    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    @Test
    void shouldAddItemsToTheShop() {
        int itemCountBefore = getDatabase().accessItemDatabase().getAllItems().size();
        AddItemToShopRequest addItemToShopRequest1 = new AddItemToShopRequest("new item 1", "1.01", "5");
        AddItemToShopRequest addItemToShopRequest2 = new AddItemToShopRequest("new item 2", "7.07", "3");
        getAddItemToShopService().execute(addItemToShopRequest1);
        getAddItemToShopService().execute(addItemToShopRequest2);
        List<Item> shopItems = getDatabase().accessItemDatabase().getAllItems();
        assertEquals(2, shopItems.size() - itemCountBefore);
        assertTrue(getDatabase().accessItemDatabase().findByName("new item 1").isPresent());
        Item newItem1 = getDatabase().accessItemDatabase().findByName("new item 1").get();
        assertEquals(new BigDecimal("1.01"), newItem1.getPrice());
        assertEquals(5, newItem1.getAvailableQuantity());
        assertTrue(getDatabase().accessItemDatabase().findByName("new item 2").isPresent());
        Item newItem2 = getDatabase().accessItemDatabase().findByName("new item 2").get();
        assertEquals(new BigDecimal("7.07"), newItem2.getPrice());
        assertEquals(3, newItem2.getAvailableQuantity());
    }

    private AddItemToShopService getAddItemToShopService() {
        return applicationContext.getBean(AddItemToShopService.class);
    }

    private Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

}
