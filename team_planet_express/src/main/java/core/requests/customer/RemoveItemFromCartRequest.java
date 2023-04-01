package core.requests.customer;

import core.support.MutableLong;
import lombok.Value;

@Value
public class RemoveItemFromCartRequest {

    MutableLong userId;
    String itemName;

}
