package item;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class Item {

    private Long id;
    private final String name;
    private final BigDecimal price;
    private Integer availableQuantity;

    public Item(String name, BigDecimal price, Integer availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", price=" + price +
                ", available quantity=" + availableQuantity;
    }

}
