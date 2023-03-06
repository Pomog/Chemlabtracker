package cart;

import data.Item;
import data.OrderedItem;
import shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<OrderedItem> orders = new ArrayList<>();

    public List<OrderedItem> getOrders() {
        return orders;
    }

    public void addItem(Item item, Integer orderedQuantity, Shop shop) {
        orders.add(new OrderedItem(item, orderedQuantity));
        shop.decreaseItemQuantity(item, orderedQuantity);
    }

}
