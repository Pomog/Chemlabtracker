package shop.acceptance.customer;

import org.junit.jupiter.api.Test;
import shop.acceptance.AcceptanceTest;
import shop.core.domain.item.Item;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SearchForItemsAcceptanceTest extends AcceptanceTest {

    @Test
    void shouldReturnAllItemsWithRobotInTheName() {
        SearchItemResponse searchItemResponse = executeSearchItem("robot", "", Collections.emptyList(), null);
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(2, searchItemResponse.getTotalFoundItemCount());
        assertEquals("Mom's Old-Fashioned Robot Oil", searchItemResponse.getItems().get(0).getName());
        assertEquals("Blank Robot", searchItemResponse.getItems().get(1).getName());
    }

    @Test
    void shouldReturnAllItemsCheaperThan10() {
        SearchItemResponse searchItemResponse = executeSearchItem("", "10", Collections.emptyList(), null);
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(6, searchItemResponse.getTotalFoundItemCount());
        Optional<Item> wrongItem = searchItemResponse.getItems().stream()
                .filter(item -> new BigDecimal("10").compareTo(item.getPrice()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
    }

    @Test
    void shouldOrderRobotItemsAscending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        SearchItemResponse searchItemResponse = executeSearchItem("robot", "", List.of(orderingRule), null);
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(2, searchItemResponse.getTotalFoundItemCount());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItems(), 7L, 4L));
    }

    @Test
    void shouldReturnSecondThreeItemPage() {
        PagingRule pagingRule = new PagingRule(2, "3");
        SearchItemResponse searchItemResponse = executeSearchItem("", "", Collections.emptyList(), pagingRule);
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(3, searchItemResponse.getItems().size());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItems(), 4L, 5L, 6L));
    }

    @Test
    void shouldReturnCorrectItemsInCorrectOrder() {
        addItem(/*11*/"Morbo on Management", new BigDecimal("4.99"), 1);
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        PagingRule pagingRule = new PagingRule(1, "4");
        SearchItemResponse searchItemResponse = executeSearchItem("T", "25", List.of(orderingRuleName, orderingRulePrice), pagingRule);
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(5, searchItemResponse.getTotalFoundItemCount());
        Optional<Item> wrongItem = searchItemResponse.getItems().stream()
                .filter(item -> new BigDecimal("25").compareTo(item.getPrice()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItems(), 11L, 6L, 4L, 9L));
        assertEquals(4, searchItemResponse.getItems().size());
        pagingRule = new PagingRule(2, "4");
        searchItemResponse = executeSearchItem("T", "25", List.of(orderingRuleName, orderingRulePrice), pagingRule);
        assertEquals(1, searchItemResponse.getItems().size());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItems(), 7L));
    }

    private boolean isOrderedCorrectly(List<Item> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

    private boolean isPageContainingCorrectItems(List<Item> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
