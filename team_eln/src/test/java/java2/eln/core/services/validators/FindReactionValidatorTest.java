package java2.eln.core.services.validators;


import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.domain.StructureData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindReactionValidatorTest {
    private StructureData mockStructureData;

    @BeforeEach
    public void setUp() {
        mockStructureData = mock(StructureData.class);
        when(mockStructureData.getSmiles()).thenReturn("C");
    }


    @Test
    public void shouldReturnErrorIfAllFieldsAreEmpty() {
        FindReactionRequest request = new FindReactionRequest("", "", mockStructureData, 0d);
        FindReactionValidator validator = new FindReactionValidator();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Searching parameters", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorIfAtLeastOneFieldIsNotEmpty() {
        FindReactionRequest request = new FindReactionRequest("", "Name", mockStructureData, 0d);
        FindReactionValidator validator = new FindReactionValidator();

        List<CoreError> errors = validator.validate(request);

        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorIfOrderByIsInvalid() {
        FindReactionRequest request = new FindReactionRequest("", "", mockStructureData, 1.0d);
        request.setOrderBy("invalid");
        FindReactionValidator validator = new FindReactionValidator();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals("Invalid value: invalid", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorIfOrderByIsValid() {
        FindReactionRequest request = new FindReactionRequest("", "", mockStructureData, 1.0d);
        request.setOrderBy("name");
        FindReactionValidator validator = new FindReactionValidator();

        List<CoreError> errors = validator.validate(request);

        assertEquals(0, errors.size());
    }
}