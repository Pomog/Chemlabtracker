package cart_item;

import item.Item;
import lombok.Data;

import java.util.Objects;

@Data
public class CartItem {

    private Long id;
    private final Item item;
    private Integer orderedQuantity;

    public CartItem(Item item, Integer orderedQuantity) {
        this.item = item;
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(item, cartItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    @Override
    public String toString() {
        return "name='" + item.getName() +
                ", price=" + item.getPrice() +
                ", ordered quantity=" + orderedQuantity;
    }

}
