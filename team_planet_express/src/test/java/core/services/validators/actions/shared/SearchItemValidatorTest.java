package core.services.validators.actions.shared;

import core.requests.shared.SearchItemRequest;
import core.services.exception.InternalSystemCollapseException;
import core.services.validators.universal.user_input.InputStringValidator;
import core.services.validators.universal.user_input.InputStringValidatorRecord;
import core.support.paging.PagingRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchItemValidatorTest {

    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private SearchItemRequest mockRequest;
    @Mock private PagingRule mockPagingRule;

    @InjectMocks private SearchItemValidator validator;

    @Test
    void shouldThrowInternalSystemCollapseExceptionForMissingPageNumber() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRule.getPageNumber()).thenReturn(null);
        assertThrows(InternalSystemCollapseException.class, () -> validator.validate(mockRequest));
    }

    @Test
    void shouldThrowInternalSystemCollapseExceptionForInvalidPageNumber() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRule.getPageNumber()).thenReturn(0);
        assertThrows(InternalSystemCollapseException.class, () -> validator.validate(mockRequest));
    }

    @Test
    void shouldNotThrowExceptionForValidPageNumber() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        assertDoesNotThrow(() -> validator.validate(mockRequest));
    }

    @Test
    void shouldValidatePageSize() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        validator.validate(mockRequest);
        InputStringValidatorRecord record = new InputStringValidatorRecord("10", "page_size", "Page size");
        verify(mockInputStringValidator).validateIsPresent(record);
        verify(mockInputStringValidator).validateIsNumber(record);
        verify(mockInputStringValidator).validateIsGreaterThanZero(record);
        verify(mockInputStringValidator).validateIsNotDecimal(record);
    }

}
