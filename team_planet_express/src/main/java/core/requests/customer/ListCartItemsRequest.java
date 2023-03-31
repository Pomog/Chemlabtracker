package core.requests.customer;

import core.support.MutableLong;
import lombok.Value;

@Value
public class ListCartItemsRequest {

    MutableLong userId;

}
