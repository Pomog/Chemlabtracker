package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.item.Item;

import java.math.BigDecimal;

public class ItemMatcher implements ArgumentMatcher<Item> {

    private final String name;
    private final BigDecimal price;
    private final Integer availableQuantity;

    public ItemMatcher(String name, BigDecimal price, Integer availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean matches(Item item) {
        return name.equals(item.getName()) &&
                price.equals(item.getPrice()) &&
                availableQuantity.equals(item.getAvailableQuantity());
    }

}
