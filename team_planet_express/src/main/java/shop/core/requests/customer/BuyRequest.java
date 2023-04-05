package shop.core.requests.customer;

import lombok.Value;
import shop.core.support.CurrentUserId;

@Value
public class BuyRequest {

    CurrentUserId userId;

}
