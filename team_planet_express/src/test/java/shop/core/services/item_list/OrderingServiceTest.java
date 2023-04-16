package shop.core.services.item_list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.core.domain.item.Item;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderingServiceTest {

    private static final Item item1 = new Item("aaa", new BigDecimal("10"), 10);
    private static final Item item2 = new Item("zzz", new BigDecimal("20"), 10);
    private static final Item item3 = new Item("aaa", new BigDecimal("20"), 10);
    private static final Item item4 = new Item("zzz", new BigDecimal("10"), 10);
    private static final Item item5 = new Item("UwU", new BigDecimal("17"), 10);

    private List<Item> items;

    private final OrderingService service = new OrderingService();

    @BeforeAll
    static void setupItemId() {
        item1.setId(1L);
        item2.setId(2L);
        item3.setId(3L);
        item4.setId(4L);
        item5.setId(5L);
    }

    @BeforeEach
    void setupItemList() {
        items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
    }

    @Test
    void shouldNotOrderForNullOrderingRules() {
        List<OrderingRule> orderingRules = null;
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldNotOrderForEmptyOrderingRules() {
        List<OrderingRule> orderingRules = new ArrayList<>();
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldOrderByName() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 3L, 5L, 2L, 4L));
    }

    @Test
    void shouldOrderByNameDescending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 4L, 5L, 1L, 3L));
    }

    @Test
    void shouldOrderByPrice() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 4L, 5L, 2L, 3L));
    }

    @Test
    void shouldOrderByPriceDescending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 3L, 5L, 1L, 4L));
    }

    @Test
    void shouldOrderByNameAndPrice() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 3L, 5L, 4L, 2L));
    }

    @Test
    void shouldOrderByNameDescendingAndPrice() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(4L, 2L, 5L, 1L, 3L));
    }

    @Test
    void shouldOrderByNameAndPriceDescending() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(3L, 1L, 5L, 2L, 4L));
    }

    @Test
    void shouldOrderByNameDescendingAndPriceDescending() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 4L, 5L, 3L, 1L));
    }

    private boolean isOrderedCorrectly(Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
