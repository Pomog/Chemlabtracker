package core.requests.customer;

import core.support.MutableLong;
import lombok.Value;

@Value
public class AddItemToCartRequest {

    MutableLong userId;
    String itemName;
    String orderedQuantity;

}
