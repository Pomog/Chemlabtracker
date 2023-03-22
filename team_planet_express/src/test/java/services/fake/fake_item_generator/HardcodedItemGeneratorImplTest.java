package services.fake.fake_item_generator;

import domain.item.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HardcodedItemGeneratorImplTest {

    private final ItemGenerator itemGenerator = new HardcodedItemGeneratorImpl();

    @Test
    void shouldCreateListOf10Items() {
        List<Item> items = itemGenerator.createItems();
        assertEquals(10, items.size());
        assertTrue(items.get(0).getName().contains("Stop"));
    }

}
