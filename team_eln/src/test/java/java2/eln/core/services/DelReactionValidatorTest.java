package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.database.InMemoryDatabaseImplIM;
import java2.eln.core.requests.DeleteReactionRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.DelReactionValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DelReactionValidatorTest {

    @Disabled
    @Test
    public void testNotEmptyCode() {
        DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();

        DeleteReactionRequest deleteReactionRequest = new DeleteReactionRequest("TP1");
        DelReactionValidator delReactionValidator = new DelReactionValidator();

        CoreError noReactionInDatabaseError =
                new CoreError("Reaction code not found", "enter the code of the reaction existing in the database");
        assertEquals(noReactionInDatabaseError.getMessage(), delReactionValidator.validate(deleteReactionRequest).get(0).getMessage());
        assertEquals(noReactionInDatabaseError.getField(), delReactionValidator.validate(deleteReactionRequest).get(0).getField());
    }

}