package shop.core.requests.manager;

import lombok.Value;

@Value
public class AddItemToShopRequest {

    String itemName;
    String price;
    String availableQuantity;

}
