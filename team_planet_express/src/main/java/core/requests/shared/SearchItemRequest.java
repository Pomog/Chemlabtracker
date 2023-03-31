package core.requests.shared;

import lombok.Value;

import java.util.List;

@Value
public class SearchItemRequest {

    String itemName;
    String price;
    List<Ordering> orderings;

}
