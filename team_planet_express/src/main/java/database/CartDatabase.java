package database;

import domain.cart.Cart;
import domain.cart.CartStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CartDatabase {

    void save(Cart cart);

    Optional<Cart> findOpenCartForUserId(Long userId);

    void changeCartStatus(Long id, CartStatus cartStatus);

    void changeLastActionDate(Long id, LocalDate newLastActionDate);

    List<Cart> getAllCarts();

}
