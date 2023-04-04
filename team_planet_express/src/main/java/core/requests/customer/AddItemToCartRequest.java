package core.requests.customer;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class AddItemToCartRequest {

    CurrentUserId userId;
    String itemName;
    String orderedQuantity;

}
