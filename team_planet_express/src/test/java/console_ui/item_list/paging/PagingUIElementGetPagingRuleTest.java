package console_ui.item_list.paging;

import console_ui.UserCommunication;
import core.support.paging.PagingRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagingUIElementGetPagingRuleTest {

    @Mock private UserCommunication mockUserCommunication;

    @InjectMocks private PagingUIElement pagingUIElement;

    @Test
    void shouldReturnPagingRule() {
        when(mockUserCommunication.getInput()).thenReturn("10");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNotNull(result);
        assertEquals(result.getPageNumber(), 1);
        assertEquals(result.getPageSize(), "10");
    }

    @Test
    void shouldReturnNullForNullPageSize() {
        when(mockUserCommunication.getInput()).thenReturn(null);
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

    @Test
    void shouldReturnNullForBlankPageSize() {
        when(mockUserCommunication.getInput()).thenReturn("");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

    @Test
    void shouldReturnNullForEmptyPageSize() {
        when(mockUserCommunication.getInput()).thenReturn(" ");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

}
