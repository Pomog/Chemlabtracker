package shop.core.services.validators.item_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import shop.core.support.paging.PagingRule;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagingRuleValidatorTest {

    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private PagingRule mockPagingRule;

    @InjectMocks private PagingRuleValidator validator;

    @Test
    void shouldThrowInternalSystemCollapseExceptionForMissingPageNumber() {
        when(mockPagingRule.getPageNumber()).thenReturn(null);
        assertThrows(InternalSystemCollapseException.class, () -> validator.validate(mockPagingRule));
    }

    @Test
    void shouldThrowInternalSystemCollapseExceptionForInvalidPageNumber() {
        when(mockPagingRule.getPageNumber()).thenReturn(0);
        assertThrows(InternalSystemCollapseException.class, () -> validator.validate(mockPagingRule));
    }

    @Test
    void shouldNotThrowExceptionForValidPageNumber() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        assertDoesNotThrow(() -> validator.validate(mockPagingRule));
    }

    @Test
    void shouldValidatePageSize() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        validator.validate(mockPagingRule);
        InputStringValidatorRecord record = new InputStringValidatorRecord("10", "page_size", "Page size");
        verify(mockInputStringValidator).validateIsPresent(record);
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsGreaterThanZero(record);
        verify(mockInputStringValidator).validateIsNotDecimal(record);
    }

}
