package shop.core.database;

import lombok.Data;
import shop.core.domain.cart_item.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class InMemoryCartItemDatabaseImpl implements CartItemDatabase {

    private Long nextId = 1L;
    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public CartItem save(CartItem cartItem) {
        cartItem.setId(nextId);
        nextId++;
        cartItems.add(cartItem);
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getCartId().equals(cartId))
                .filter(cartItem -> cartItem.getItemId().equals(itemId))
                .findFirst();
    }

    @Override
    public void deleteByID(Long idToRemove) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getId().equals(idToRemove))
                .findFirst()
                .ifPresent(cartItems::remove);
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getId().equals(id))
                .findFirst()
                .ifPresent(cartItem -> cartItem.setOrderedQuantity(newOrderedQuantity));
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItems;
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getCartId().equals(cartId))
                .collect(Collectors.toList());
    }
}
