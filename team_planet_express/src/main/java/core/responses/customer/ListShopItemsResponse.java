package core.responses.customer;

import core.domain.item.Item;

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
