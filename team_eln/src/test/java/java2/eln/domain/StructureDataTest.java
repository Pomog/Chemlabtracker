package java2.eln.domain;

import java2.eln.core.domain.StructureData;
import org.junit.jupiter.api.Test;
import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Bond;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IMolecularFormula;
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

    @Test
    public void testInvalidSmilesSetSmiles() {
        String invalidSmiles = "invalidSmiles";
        StructureData sd = new StructureData(invalidSmiles);
        assertNotNull(sd.getMol());
        assertEquals("C", sd.getSmiles());
    }


    @Test
    public void testInvalidSmilesReturnMethane() {
        String invalidSmiles = "invalidSmiles";
        StructureData sd = new StructureData(invalidSmiles);
        assertNotNull(sd.getMol());
        assertEquals("CH4", sd.getBruttoFormula());
    }

    @Test
    public void parseInvalidSmilesTest() {
        String smiles = "C";
        StructureData sd = new StructureData(smiles);
        IAtomContainer parsedAtomContainer = sd.getMol();

        IAtomContainer methane = new AtomContainer();
        Atom carbon = new Atom("C");
        methane.addAtom(carbon);
        for (int i = 0; i < 4; i++) {
            Atom hydrogen = new Atom("H");
            methane.addAtom(hydrogen);
            methane.addBond(new Bond(carbon, hydrogen, IBond.Order.SINGLE));
        }
        IMolecularFormula methaneMol = MolecularFormulaManipulator.getMolecularFormula(methane);

        assertEquals(sd.getBruttoFormula(), MolecularFormulaManipulator.getString(methaneMol));
    }
}