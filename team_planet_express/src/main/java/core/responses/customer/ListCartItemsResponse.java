package core.responses.customer;

import core.domain.cart_item.CartItem;
import core.responses.CoreError;
import core.responses.CoreResponse;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsResponse extends CoreResponse {
    private List<CartItem> cartItems;
    private BigDecimal cartTotal;

    public ListCartItemsResponse(List<CoreError> errors) {
        super(errors);
    }

    public ListCartItemsResponse(List<CartItem> cartItems, BigDecimal cartTotal) {
        this.cartItems = cartItems;
        this.cartTotal = cartTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }
}
