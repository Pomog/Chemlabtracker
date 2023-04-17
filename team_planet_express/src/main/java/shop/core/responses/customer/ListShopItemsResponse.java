package shop.core.responses.customer;

import shop.core.domain.item.Item;
import shop.core.responses.CoreResponse;

import java.util.List;

public class ListShopItemsResponse extends CoreResponse {

    private final List<Item> shopItems;

    public ListShopItemsResponse(List<Item> shopItems) {
        this.shopItems = shopItems;
    }

    public List<Item> getShopItems() {
        return shopItems;
    }

}
