package baseClasses;

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
    private String internalCode;
    private double mass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StructureData(String smiles, String name, double mass) {
        this.smiles = smiles;
        this.name = name;
        this.mass = mass;
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
    public void printMW(){
        String message = String.format("MW = [%.2f]", mw);
        System.out.println(message);
    }
    public double getMW() {
        return mw;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "baseClasses.StructureData{" +
                "smiles='" + smiles + '\'' +
                ", formula=" + MolecularFormulaManipulator.getString(formula) +
                ", mw=" + String.format("%.2f", mw) +
                ", casNumber='" + casNumber + '\'' +
                ", name='" + name + '\'' +
                ", internalCode='" + internalCode + '\'' +
                ", mass=" + mass +
                '}';
    }
}