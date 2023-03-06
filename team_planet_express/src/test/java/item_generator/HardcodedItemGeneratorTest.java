package item_generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HardcodedItemGeneratorTest {

    private final ItemGenerator itemGenerator = new HardcodedItemGenerator();

    @Test
    void shouldCreateListOfItems() {
        assertEquals(10, itemGenerator.createItems().size());
        assertTrue(itemGenerator.createItems().get(0).getName().contains("Stop"));
    }

}
