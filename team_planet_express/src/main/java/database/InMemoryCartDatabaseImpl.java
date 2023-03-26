package database;

import cart.Cart;
import cart.CartStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InMemoryCartDatabaseImpl implements CartDatabase {

    private Long nextId = 1L;
    private final List<Cart> carts = new ArrayList<>();

    @Override
    public void save(Cart cart) {
        cart.setId(nextId);
        nextId++;
        carts.add(cart);
    }

    @Override
    public void changeCartStatus(Long id, CartStatus newCartStatus) {
        carts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst()
                .ifPresent(cart -> cart.setCartStatus(newCartStatus));
    }

    @Override
    public void changeLastActionDate(Long id, LocalDate newLastActionDate) {
        carts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst()
                .ifPresent(cart -> cart.setLastActionDate(LocalDate.now()));
    }

    @Override
    public List<Cart> getAllCarts() {
        return carts;
    }

}
