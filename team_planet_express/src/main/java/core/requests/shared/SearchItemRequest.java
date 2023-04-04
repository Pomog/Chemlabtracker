package core.requests.shared;

import core.support.ordering.OrderingRule;
import core.support.paging.PagingRule;
import lombok.Value;

import java.util.List;

@Value
public class SearchItemRequest {

    String itemName;
    String price;
    List<OrderingRule> orderingRules;
    PagingRule pagingRule;

}
