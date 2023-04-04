package core.domain.cart_item;

import lombok.Data;

import java.util.Objects;

@Data
public class CartItem {

    private Long id;
    private final Long cartId;
    private final Long itemId;
    private Integer orderedQuantity;

    public CartItem(Long cartId, Long itemId, Integer orderedQuantity) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(cartId, cartItem.cartId) && Objects.equals(itemId, cartItem.itemId) && Objects.equals(orderedQuantity, cartItem.orderedQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, itemId, orderedQuantity);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", cartId=" + cartId +
                ", itemId=" + itemId +
                ", orderedQuantity=" + orderedQuantity;
    }

}
