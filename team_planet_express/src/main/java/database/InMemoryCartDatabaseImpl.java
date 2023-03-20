package database;

import domain.cart.Cart;
import domain.cart.CartStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        return carts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .filter(cart -> cart.getCartStatus().equals(CartStatus.OPEN))
                .findFirst();
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
