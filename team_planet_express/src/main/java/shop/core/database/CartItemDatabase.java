package shop.core.database;

import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemDatabase {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId);

    void deleteByID(Long id);

    void changeOrderedQuantity(Long id, Integer newOrderedQuantity);

    List<CartItem> getAllCartItems();

    List<CartItem> getAllCartItemsForCartId(Long cartId);

}
