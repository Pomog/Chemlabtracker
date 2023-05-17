package java2.eln.core.services;

import java2.eln.core.requests.AddReactionRequest;
import java2.eln.core.services.validators.AddReactionValidator;
import java2.eln.core.domain.ReactionData;
import java2.eln.core.domain.StructureData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddReactionValidatorTest {

    @Test
    public void testNotEmptyCodeAndName() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("TP1", "The Friedel-Crafts acylation", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        assertTrue(addReactionValidator.validate(addReactionRequest).isEmpty());
    }
    @Test
    public void testNotEmptyCode() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("TP1", "", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        assertFalse(addReactionValidator.validate(addReactionRequest).isEmpty());
        assertEquals("Reaction Name",
                addReactionValidator.validate(addReactionRequest).get(0).getField()
        );
        assertEquals("Must not be empty!",
                addReactionValidator.validate(addReactionRequest).get(0).getMessage()
        );
    }
    @Test
    public void testNotEmptyName() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("", "The Friedel-Crafts acylation", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        assertFalse(addReactionValidator.validate(addReactionRequest).isEmpty());
        assertEquals("Reaction Code",
                addReactionValidator.validate(addReactionRequest).get(0).getField()
        );
        assertEquals("Must not be empty!",
                addReactionValidator.validate(addReactionRequest).get(0).getMessage()
        );
    }

    @Test
    public void smilesError (){
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("TP1", "The Friedel-Crafts acylation", "data/demoReactionTest.txt");
        CreateStructureFromFile newMaterialTest = new CreateStructureFromFile(addReactionRequest.getFilename());
        ReactionData reactionDataTest = new ReactionData(addReactionRequest.getCode(), addReactionRequest.getName());
        StructureData structureDataTest = newMaterialTest.readFromFile("SM1");


        System.out.println(structureDataTest.getMW());
    }
}