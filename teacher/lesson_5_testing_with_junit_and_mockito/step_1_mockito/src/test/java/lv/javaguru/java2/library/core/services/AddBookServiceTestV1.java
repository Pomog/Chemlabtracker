package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTestV1 {

    @Mock private Database database;
    @Mock private AddBookRequestValidator validator;
    @InjectMocks
    private AddBookService service;


    @Test
    public void shouldFail() {
        AddBookService service = new AddBookService(null, new FailAddBookRequestValidator());
        AddBookRequest request = new AddBookRequest("title", "author");
        AddBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldSuccess() {
        AddBookService service = new AddBookService(
                new FaikDatabase(),
                new SuccessAddBookRequestValidator());
        AddBookRequest request = new AddBookRequest("title", "author");
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldFail_Mockito_style() {
        AddBookRequest request = new AddBookRequest("title", "author");
        AddBookRequestValidator validator = mock(AddBookRequestValidator.class);
        when(validator.validate(request)).thenReturn(
                List.of(new CoreError("fff", "description"))
        );
        AddBookService service = new AddBookService(null, validator);
        AddBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldSuccess_Mockito_style() {
        AddBookRequest request = new AddBookRequest("title", "author");
        AddBookRequestValidator validator = mock(AddBookRequestValidator.class);
        when(validator.validate(request)).thenReturn(List.of());
        Database database = mock(Database.class);
        AddBookService service = new AddBookService(database, validator);
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(database).save(any());
    }


    @Test
    public void shouldFail_Mockito_style_runner() {
        AddBookRequest request = new AddBookRequest("title", "author");
        when(validator.validate(request)).thenReturn(
                List.of(new CoreError("fff", "description"))
        );
        AddBookResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldSuccess_Mockito_style_runner() {
        AddBookRequest request = new AddBookRequest("title", "author");
        when(validator.validate(request)).thenReturn(List.of());
        AddBookResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(database).save(any());
    }


    class FaikDatabase extends InMemoryDatabaseImpl {

    }

    class SuccessAddBookRequestValidator extends AddBookRequestValidator {
        @Override
        public List<CoreError> validate(AddBookRequest request) {
            return List.of();
        }
    }

    class FailAddBookRequestValidator extends AddBookRequestValidator {
        @Override
        public List<CoreError> validate(AddBookRequest request) {
            return List.of(new CoreError("fff", "description"));
        }
    }

}
