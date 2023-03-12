package database;

import cart_item.CartItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InMemoryCartItemDatabaseImpl implements CartItemDatabase {

    private Long nextId = 1L;
    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public void save(CartItem cartItem) {
        cartItem.setId(nextId);
        nextId++;
        cartItems.add(cartItem);
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

}
