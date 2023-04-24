package shop.core.services.item_list;

import shop.core.domain.item.Item;
import shop.core.support.paging.PagingRule;
import shop.dependency_injection.DIComponent;

import java.util.List;
import java.util.stream.Collectors;

@DIComponent
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
