package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.FindReactionResponse;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.FindReactionValidator;
import java2.eln.domain.ReactionData;
import java2.eln.domain.StructureData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindReactionServiceTest {
    @Disabled
    @Test
    void execute() {
        // Create mock objects
        DatabaseIM databaseIMTest = mock(DatabaseIM.class);
        FindReactionValidator validator = mock(FindReactionValidator.class);

        // Set up mock behavior for validator
        List<CoreError> errors = new ArrayList<>();
        when(validator.validate(any(FindReactionRequest.class))).thenReturn(errors);

        // Set up mock behavior for databaseIMTest
        ReactionData demoReactionLog = new ReactionData("TP1", "The Friedel-Crafts acylation");
        StructureData sm1 = new StructureData("C1=CC=CC=C1", "Isobutylbenzene",4.03);
        StructureData sm2 = new StructureData("O=C(C)C", "Acetic anhydride", 3.06);
        StructureData sm3 = new StructureData("N#C");
        StructureData mp = new StructureData("CC(=O)C", "4-Isobutylacetophenone", 1.1);
        demoReactionLog.addStartingMaterial(sm1);
        demoReactionLog.addStartingMaterial(sm2);
        demoReactionLog.addStartingMaterial(sm3);
        demoReactionLog.setMainProduct(mp);
        demoReactionLog.calculateReactionYield();
        when(databaseIMTest.getAllReactions()).thenReturn(List.of(demoReactionLog));

        // Create the service and execute it with a valid request
        FindReactionService service = new FindReactionService();
        FindReactionRequest request = new FindReactionRequest("TP1", null, null, null );
        FindReactionResponse response = service.execute(request);

        // Verify that the response contains the expected result
        List<ReactionData> expectedResults = List.of(demoReactionLog);

        System.out.println(expectedResults);

        System.out.println(response.getSearchingResults());

        assert(response.getSearchingResults().equals(expectedResults));



    }
}