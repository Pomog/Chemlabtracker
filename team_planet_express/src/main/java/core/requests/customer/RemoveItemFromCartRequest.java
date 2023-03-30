package core.requests.customer;

import lombok.Value;

@Value
public class RemoveItemFromCartRequest {

    Long userId;
    String itemName;

}
