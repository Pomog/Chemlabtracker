package shop.acceptance.manager;

import org.junit.jupiter.api.Test;
import shop.acceptance.AcceptanceTest;
import shop.core.domain.item.Item;
import shop.core.responses.CoreError;
import shop.core.responses.manager.ChangeItemDataResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeItemDataAcceptanceTest extends AcceptanceTest {

    @Test
    void shouldChangeItemNameOnly() {
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("6", "Delivery-Boy Man", "", "");
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = getDatabase().accessItemDatabase().findById(6L).orElseThrow();
        assertCorrectItemChanges(item, "Delivery-Boy Man", new BigDecimal("24.99"), 70);
    }

    @Test
    void shouldChangePriceOnly() {
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("10", "", "1000.00", "");
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = getDatabase().accessItemDatabase().findById(10L).orElseThrow();
        assertCorrectItemChanges(item, "Popplers", new BigDecimal("1000.00"), 0);
    }


    @Test
    void shouldChangeQuantityOnly() {
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("1", "", "", "0");
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = getDatabase().accessItemDatabase().findById(1L).orElseThrow();
        assertCorrectItemChanges(item, "Stop-and-Drop Suicide Booth", new BigDecimal("1000.00"), 0);
    }

    @Test
    void shouldChangeEverything() {
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("2", "Good News Everyone", "7.77", "100");
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = getDatabase().accessItemDatabase().findById(2L).orElseThrow();
        assertCorrectItemChanges(item, "Good News Everyone", new BigDecimal("7.77"), 100);
    }

    @Test
    void shouldReturnErrorForDuplicate() {
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("4", "Lightspeed Briefs", "249.99", "50");
        assertTrue(changeItemDataResponse.hasErrors());
        List<CoreError> errors = changeItemDataResponse.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Error: Exactly the same item already exists.", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorForDuplicate2() {
        addItem(/*11*/"Angry Norwegian Anchovies", new BigDecimal("249.99"), 1);
        ChangeItemDataResponse changeItemDataResponse = executeChangeItemData("11", "Lightspeed Briefs", "", "");
        assertTrue(changeItemDataResponse.hasErrors());
        List<CoreError> errors = changeItemDataResponse.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Error: Exactly the same item already exists.", errors.get(0).getMessage());
    }

    private void assertCorrectItemChanges(Item item, String itemName, BigDecimal price, Integer quantity) {
        assertEquals(itemName, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getAvailableQuantity());
    }

}
