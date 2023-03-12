package database;

import cart_item.CartItem;

import java.util.List;

public interface CartItemDatabase {

    void save(CartItem cartItem);

    void deleteByID(Long id);

    void changeOrderedQuantity(Long id, Integer newOrderedQuantity);

    List<CartItem> getAllCartItems();

}
