package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.database.InMemoryDatabaseImplIM;
import java2.eln.core.requests.DelReactionRequest;
import java2.eln.core.responses.CoreError;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DelReactionValidatorTest {

    @Test
    public void testNotEmptyCode() {
        DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();

        DelReactionRequest delReactionRequest = new DelReactionRequest("TP1");
        DelReactionValidator delReactionValidator = new DelReactionValidator(inMemoryDataBase);

        CoreError noReactionInDatabaseError =
                new CoreError("Reaction code not found", "enter the code of the reaction existing in the database");
        assertEquals(noReactionInDatabaseError.getMessage(), delReactionValidator.validate(delReactionRequest).get(0).getMessage());
        assertEquals(noReactionInDatabaseError.getField(), delReactionValidator.validate(delReactionRequest).get(0).getField());
    }

}