package core.requests.customer;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class BuyRequest {

    CurrentUserId userId;

}
