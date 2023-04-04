package console_ui.item_list.ordering;

import console_ui.UserCommunication;
import core.support.ordering.OrderBy;
import core.support.ordering.OrderDirection;
import core.support.ordering.OrderingRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderingUIElementTest {

    @Mock private UserCommunication mockUserCommunication;

    @InjectMocks private OrderingUIElement orderingUIElement;

    @Test
    void shouldReturnEmptyOrderingRulesList() {
        when(mockUserCommunication.getInput()).thenReturn("");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOrderingRulesListWithName() {
        when(mockUserCommunication.getInput()).thenReturn("y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(0).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithPrice() {
        when(mockUserCommunication.getInput()).thenReturn("", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(OrderBy.PRICE, result.get(0).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(0).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPrice() {
        when(mockUserCommunication.getInput()).thenReturn("y", "", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(0).getOrderDirection());
        assertEquals(OrderBy.PRICE, result.get(1).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(1).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameDescending() {
        when(mockUserCommunication.getInput()).thenReturn("y", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(0).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithPriceDescending() {
        when(mockUserCommunication.getInput()).thenReturn("", "y", "y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(OrderBy.PRICE, result.get(0).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(0).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameDescendingAndPrice() {
        when(mockUserCommunication.getInput()).thenReturn("y", "y", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(0).getOrderDirection());
        assertEquals(OrderBy.PRICE, result.get(1).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(1).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPriceDescending() {
        when(mockUserCommunication.getInput()).thenReturn("y", "", "y", "y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.ASCENDING, result.get(0).getOrderDirection());
        assertEquals(OrderBy.PRICE, result.get(1).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(1).getOrderDirection());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPriceBothDescending() {
        when(mockUserCommunication.getInput()).thenReturn("y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(0).getOrderDirection());
        assertEquals(OrderBy.PRICE, result.get(1).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(1).getOrderDirection());
    }

    @Test
    void shouldAcceptCapitalLetters() {
        when(mockUserCommunication.getInput()).thenReturn("Y", "Y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(OrderBy.NAME, result.get(0).getOrderBy());
        assertEquals(OrderDirection.DESCENDING, result.get(0).getOrderDirection());
    }

}
