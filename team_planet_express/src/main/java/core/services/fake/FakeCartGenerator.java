package core.services.fake;

import core.domain.cart.Cart;
import core.domain.user.User;

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
