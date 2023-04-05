package shop.core.services.item_list.paging;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.core.domain.item.Item;
import shop.core.support.paging.PagingRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagingServiceTest {

    private static final Item item1 = new Item("UwU", new BigDecimal("420"), 69);
    private static final Item item2 = new Item("UwU", new BigDecimal("420"), 69);
    private static final Item item3 = new Item("UwU", new BigDecimal("420"), 69);
    private static final Item item4 = new Item("UwU", new BigDecimal("420"), 69);
    private static final Item item5 = new Item("UwU", new BigDecimal("420"), 69);

    private List<Item> items;

    private final PagingService service = new PagingService();

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
    void shouldReturnAllForNullPagingRules() {
        PagingRule pagingRule = null;
        items = service.getPage(items, pagingRule);
        assertEquals(5, items.size());
        assertTrue(isPageContainingCorrectItems(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldReturnAllForGiantPage() {
        PagingRule pagingRule = new PagingRule(1, "100");
        items = service.getPage(items, pagingRule);
        assertEquals(5, items.size());
        assertTrue(isPageContainingCorrectItems(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldReturnFirstPage() {
        PagingRule pagingRule = new PagingRule(1, "2");
        items = service.getPage(items, pagingRule);
        assertEquals(2, items.size());
        assertTrue(isPageContainingCorrectItems(1L, 2L));
    }

    @Test
    void shouldReturnSecondPage() {
        PagingRule pagingRule = new PagingRule(2, "2");
        items = service.getPage(items, pagingRule);
        assertEquals(2, items.size());
        assertTrue(isPageContainingCorrectItems(3L, 4L));
    }

    @Test
    void shouldReturnThirdPartialPage() {
        PagingRule pagingRule = new PagingRule(3, "2");
        items = service.getPage(items, pagingRule);
        assertEquals(1, items.size());
        assertTrue(isPageContainingCorrectItems(5L));
    }

    @Test
    void shouldReturnBigPage() {
        PagingRule pagingRule = new PagingRule(1, "4");
        items = service.getPage(items, pagingRule);
        assertEquals(4, items.size());
        assertTrue(isPageContainingCorrectItems(1L, 2L, 3L, 4L));
    }

    private boolean isPageContainingCorrectItems(Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
