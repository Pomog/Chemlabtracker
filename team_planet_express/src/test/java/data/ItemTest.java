package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @BeforeEach
    void resetIdCounter() {
        Item.setIdCounter(1L);
    }

    @Test
    void shouldAutomaticallyAddId() {
        Item item = new Item("name", new BigDecimal("1.00"), 5);
        assertEquals(1, item.getId());
    }

    @Test
    void shouldAutomaticallyIncreaseId() {
        List<Item> items = new ArrayList<>();
        Long counter = 285014L;
        for (int i = 1; i <= counter; i++) {
            items.add(new Item("name", new BigDecimal("1.00"), 5));
        }
        assertEquals(counter, items.get(counter.intValue() - 1).getId());
    }

    @Test
    void shouldDecreaseAvailableAmount() {
        Item item = new Item("name", new BigDecimal("1.00"), 5);
        item.decreaseQuantityAvailable(2);
        assertEquals(3, item.getQuantityAvailable());
    }

}
