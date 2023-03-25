package java2.eln.core.services;

import java2.eln.domain.StructureData;
import org.junit.jupiter.api.Test;
import org.openscience.cdk.interfaces.IAtomContainer;
import static org.junit.jupiter.api.Assertions.*;

class CreateStructureFromFileTest {

    String smilesIsobutylbenzene = "CC(C)CC1=CC=CC=C1";
    String carbon = "C";
    String testFile = "data/test.txt";
    CreateStructureFromFile createStructureFromFile = new CreateStructureFromFile(testFile);

    @Test
    void positiveSmilesTest() {
        StructureData testStructure = createStructureFromFile.readFromFile("Test1");
        assertEquals(smilesIsobutylbenzene, testStructure.getSmiles());
    }

    @Test
    void negativeSmilesTest() {
        StructureData testStructure = createStructureFromFile.readFromFile("Test2");
        IAtomContainer mol = testStructure.getMol();
        double expectedMW = 12.011;
        assertEquals(expectedMW, testStructure.getMW(), 0.001);
    }
}