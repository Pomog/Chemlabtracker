package java2.eln.core.requests;

import java2.eln.core.domain.StructureData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindReactionRequestTest {

    @Test
    void getYieldTwoDecimals() {
        FindReactionRequest findReactionRequestTest =
                new FindReactionRequest("","", new StructureData("C"), 90.02);
        double testYield = 90.02;
        assertEquals(testYield, findReactionRequestTest.getYield() );
    }

    @Test
    void getYieldOneDecimal() {
        FindReactionRequest findReactionRequestTest =
                new FindReactionRequest("","", new StructureData("C"), 90.2);
        double testYield = 90.2;
        assertEquals(testYield, findReactionRequestTest.getYield() );
    }

    @Test
    void getYieldThreeDecimalLow() {
        FindReactionRequest findReactionRequestTest =
                new FindReactionRequest("","", new StructureData("C"), 90.225);
        double testYield = 90.22;
        assertEquals(testYield, findReactionRequestTest.getFormattedYield() );
    }

    @Test
    void getYieldThreeDecimalHigh() {
        FindReactionRequest findReactionRequestTest =
                new FindReactionRequest("","", new StructureData("C"), 90.2251);
        double testYield = 90.23;
        assertEquals(testYield, findReactionRequestTest.getFormattedYield() );
    }

    @Test
    void emptySmilesTest() {
        FindReactionRequest findReactionRequestTest =
                new FindReactionRequest("","empty", new StructureData(""), 90.2251);
        assertEquals(new StructureData("C"), findReactionRequestTest.getStartingMaterial());
    }
}