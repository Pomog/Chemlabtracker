package shop.core.services.validators.actions.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.item_list.OrderingRuleValidator;
import shop.core.services.validators.item_list.PagingRuleValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.support.paging.PagingRule;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchItemValidatorTest {

    @Mock private InputStringValidator mockInputStringValidator;
    @Mock private OrderingRuleValidator mockOrderingRuleValidator;
    @Mock private PagingRuleValidator mockPagingRuleValidator;
    @Mock private SearchItemRequest mockRequest;
    @Mock private PagingRule mockPagingRule;
    @Mock private CoreError mockCoreError;

    @InjectMocks private SearchItemValidator validator;

    @Test
    void shouldValidatePagingRuleIfPresent() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        validator.validate(mockRequest);
        verify(mockPagingRuleValidator).validate(mockPagingRule);
    }

    @Test
    void shouldNotValidatePagingRuleIfNotPresent() {
        when(mockRequest.getPagingRule()).thenReturn(null);
        validator.validate(mockRequest);
        verify(mockPagingRuleValidator, times(0)).validate(any(PagingRule.class));
    }

    @Test
    void shouldReturnNoErrorsWhenPagingHasNoErrors() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRuleValidator.validate(mockPagingRule)).thenReturn(Collections.emptyList());
        List<CoreError> errors = validator.validate(mockRequest);
        assertEquals(0, errors.size());
    }

    @Test
    void shouldReturnErrorsWhenPagingHasErrors() {
        when(mockRequest.getPagingRule()).thenReturn(mockPagingRule);
        when(mockPagingRuleValidator.validate(mockPagingRule)).thenReturn(List.of(mockCoreError));
        when(mockCoreError.getField()).thenReturn("field");
        when(mockCoreError.getMessage()).thenReturn("has errors");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 0);
        Optional<CoreError> error = errors.stream()
                .filter(coreError -> coreError.getField().equals("field"))
                .filter(coreError -> coreError.getMessage().toLowerCase().contains("errors"))
                .findFirst();
        assertFalse(error.isEmpty());
    }

}
