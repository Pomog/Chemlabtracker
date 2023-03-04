import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;

public class StructureData {
    private final String smiles; // "CC(=O)O";
    private IAtomContainer mol;
    private IMolecularFormula formula;
    private double mw;
    private String casNumber;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StructureData(String smiles, String name) {
        this.smiles = smiles;
        this.name = name;
        smilesConverter();
        calculateBruttoFormula();
        calculateMW();
    }

    public String getCasNumber() {
        return casNumber;
    }
    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }
    private void smilesConverter () {
        try {
            IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
            SmilesParser smilesParser = new SmilesParser(bldr);
            mol = smilesParser.parseSmiles(smiles);
        }
        catch (InvalidSmilesException exception){
            System.err.println("Invalid SMILES : " + exception.getMessage());
        }
    }
    private void calculateBruttoFormula(){
        formula = MolecularFormulaManipulator.getMolecularFormula(mol);
    }
    public void getBruttoFormula(){
        String formulaString = MolecularFormulaManipulator.getString(formula);
        System.out.println(formulaString);
    }
    private void calculateMW(){
        mw = MolecularFormulaManipulator.getMass(formula);
    }
    public void getMW(){
        String message = String.format("MW = [%.2f]", mw);
        System.out.println(message);
    }
}