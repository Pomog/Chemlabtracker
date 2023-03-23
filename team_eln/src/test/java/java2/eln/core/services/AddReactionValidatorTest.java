package java2.eln.core.services;

import java2.eln.core.requests.AddReactionRequest;
import org.junit.Assert;
import org.junit.Test;

public class AddReactionValidatorTest {

    @Test
    public void testNotEmptyCodeAndName() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("TP1", "The Friedel-Crafts acylation", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        Assert.assertTrue(addReactionValidator.validate(addReactionRequest).isEmpty());
    }
    @Test
    public void testNotEmptyCode() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("TP1", "", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        Assert.assertFalse(addReactionValidator.validate(addReactionRequest).isEmpty());
        Assert.assertEquals("Reaction Name",
                addReactionValidator.validate(addReactionRequest).get(0).getField()
        );
        Assert.assertEquals("Must not be empty!",
                addReactionValidator.validate(addReactionRequest).get(0).getMessage()
        );
    }
    @Test
    public void testNotEmptyName() {
        AddReactionRequest addReactionRequest =
                new AddReactionRequest("", "The Friedel-Crafts acylation", "team_eln/data/demoReaction1.txt");
        AddReactionValidator addReactionValidator =
                new AddReactionValidator();
        Assert.assertFalse(addReactionValidator.validate(addReactionRequest).isEmpty());
        Assert.assertEquals("Reaction Code",
                addReactionValidator.validate(addReactionRequest).get(0).getField()
        );
        Assert.assertEquals("Must not be empty!",
                addReactionValidator.validate(addReactionRequest).get(0).getMessage()
        );
    }
}