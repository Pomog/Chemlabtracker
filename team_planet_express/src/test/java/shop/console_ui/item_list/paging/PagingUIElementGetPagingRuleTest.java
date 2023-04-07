package shop.console_ui.item_list.paging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.support.paging.PagingRule;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagingUIElementGetPagingRuleTest {

    @Mock private UserCommunication mockUserCommunication;

    @InjectMocks private PagingUIElement pagingUIElement;

    @Test
    void shouldReturnPagingRule() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("10");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNotNull(result);
        assertEquals(result.getPageNumber(), 1);
        assertEquals(result.getPageSize(), "10");
    }

    @Test
    void shouldReturnNullForNullPageSize() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn(null);
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

    @Test
    void shouldReturnNullForBlankPageSize() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

    @Test
    void shouldReturnNullForEmptyPageSize() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn(" ");
        PagingRule result = pagingUIElement.getPagingRule();
        assertNull(result);
    }

}
