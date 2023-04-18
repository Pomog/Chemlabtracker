package shop.core.domain.cart_item;

import lombok.Data;

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
    public String toString() {
        return "id=" + id +
                ", cartId=" + cartId +
                ", itemId=" + itemId +
                ", orderedQuantity=" + orderedQuantity;
    }

}
