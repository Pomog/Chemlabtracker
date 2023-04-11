package java2.eln.domain;

import org.junit.Ignore;
import org.junit.Test;
import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;


import static org.junit.jupiter.api.Assertions.*;

public class StructureDataTest {
    @Test
    public void testValidSmiles() {
        String smiles = "CC(=O)O";
        StructureData sd = new StructureData(smiles);
        assertNotNull(sd.getMol());
        assertEquals(smiles, sd.getSmiles());
    }

    @Ignore("This test is currently not working and needs to be fixed.")
    @Test
    public void testInvalidSmilesSetSmiles() {
        String invalidSmiles = "invalidSmiles";
        StructureData sd = new StructureData(invalidSmiles);
        assertNotNull(sd.getMol());
        assertEquals(invalidSmiles, sd.getSmiles());
    }

    @Ignore("This test is currently not working and needs to be fixed.")
    @Test
    public void testInvalidSmilesReturnCarbon() {
        String invalidSmiles = "invalidSmiles";
        StructureData sd = new StructureData(invalidSmiles);
        assertNotNull(sd.getMol());
        assertEquals("C", sd.getBruttoFormula());
    }

    @Test
    public void parseSmilesTest() {
        String smiles = "C";
        StructureData sd = new StructureData(smiles);

        AtomContainer carbon = new AtomContainer();
        carbon.addAtom(new Atom("C"));




    }
}