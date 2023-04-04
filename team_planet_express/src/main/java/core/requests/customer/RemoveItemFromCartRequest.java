package core.requests.customer;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class RemoveItemFromCartRequest {

    CurrentUserId userId;
    String itemName;

}
