package core.services.item.paging;

import core.domain.item.Item;
import core.support.paging.PagingRule;

import java.util.List;
import java.util.stream.Collectors;

public class PagingService {

    public List<Item> getPage(List<Item> items, PagingRule pagingRule) {
        if (pagingRule != null) {
            long pageSize = Long.parseLong(pagingRule.getPageSize());
            long itemCountToSkip = (pagingRule.getPageNumber() - 1) * pageSize;
            items = items.stream()
                    .skip(itemCountToSkip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        }
        return items;
    }

}
