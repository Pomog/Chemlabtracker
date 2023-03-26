package database;

import cart.Cart;
import cart.CartStatus;

import java.time.LocalDate;
import java.util.List;

public interface CartDatabase {

    void save(Cart cart);

    void changeCartStatus(Long id, CartStatus cartStatus);

    void changeLastActionDate(Long id, LocalDate newLastActionDate);

    List<Cart> getAllCarts();

}
