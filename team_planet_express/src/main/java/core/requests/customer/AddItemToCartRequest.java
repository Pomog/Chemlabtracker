package core.requests.customer;

import lombok.Value;

@Value
public class AddItemToCartRequest {

    Long userId;
    String itemName;
    String orderedQuantity;

}
