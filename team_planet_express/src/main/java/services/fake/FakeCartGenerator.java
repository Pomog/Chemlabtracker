package services.fake;

import domain.cart.Cart;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class FakeCartGenerator {

    public List<Cart> createCartsForUsers(List<User> users) {
        List<Cart> fakeCarts = new ArrayList<>();
        for (User user : users) {
            fakeCarts.add(new Cart(user.getId()));
        }
        return fakeCarts;
    }

}
