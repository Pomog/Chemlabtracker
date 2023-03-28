package core.requests.shared;

import lombok.Value;

@Value
public class SearchItemRequest {
    String itemName;
    String price;
}
