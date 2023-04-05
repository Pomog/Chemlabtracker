package shop.core.services.fake.fake_item_generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomItemGeneratorImplTest {

    private final ItemGenerator itemGenerator = new RandomItemGeneratorImpl();

    @Test
    void shouldCreateListOfItems() {
        assertEquals(10, itemGenerator.createItems().size());
    }

}
