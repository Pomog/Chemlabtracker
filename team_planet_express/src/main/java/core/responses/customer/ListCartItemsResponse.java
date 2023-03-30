package core.responses.customer;

import core.domain.cart_item.CartItem;
import core.responses.CoreError;
import core.responses.CoreResponse;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsResponse extends CoreResponse {

    private List<CartItem> cartItems;
    private BigDecimal cartTotal;

    public ListCartItemsResponse(List<CartItem> cartItems, BigDecimal cartTotal) {
        this.cartItems = cartItems;
        //TODO cartTotal needs another place to call home
        this.cartTotal = cartTotal;
    }

    public ListCartItemsResponse(List<CoreError> errors) {
        super(errors);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

}
