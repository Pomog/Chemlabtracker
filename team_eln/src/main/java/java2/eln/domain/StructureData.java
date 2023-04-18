package java2.eln.domain;

import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Bond;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;

public class StructureData {
    private String smiles; // "CC(=O)O";

    private void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    private IAtomContainer mol;
    private IMolecularFormula formula;
    private double mw;

    public String getSmiles() {
        return smiles;
    }

    private String casNumber;
    private String name;
    private String internalCode;
    private double mass;

    public String getName() {
        return name;
    }
    public IAtomContainer getMol() {
        return mol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StructureData(String smiles) {
        this.smiles = smiles;
        smilesConverter();
        calculateBruttoFormula();
        calculateMW();
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

    public void smilesConverter() {
        IChemObjectBuilder builder = SilentChemObjectBuilder.getInstance();
        SmilesParser parser = new SmilesParser(builder);
        if (smiles == null || smiles.isBlank()) {
            setSmiles("C");
            setMethaneAsStructure();
            return;
        }
        try {
            mol = parser.parseSmiles(smiles);
        } catch (InvalidSmilesException e) {
            System.err.println("Invalid SMILES: " + e.getMessage());
            setSmiles("C");
            setMethaneAsStructure();
        }
    }

    private void setCarbonAsStructure() {
        mol = new AtomContainer();
        mol.addAtom(new Atom("C"));
    }

    private void setMethaneAsStructure() {
        mol = new AtomContainer();
        Atom carbon = new Atom("C");
        mol.addAtom(carbon);
        for (int i = 0; i < 4; i++) {
            Atom hydrogen = new Atom("H");
            mol.addAtom(hydrogen);
            mol.addBond(new Bond(carbon, hydrogen, Bond.Order.SINGLE));
        }
    }

    public IAtomContainer parseSmiles(String smiles) {
        try {
            SmilesParser parser = new SmilesParser(SilentChemObjectBuilder.getInstance());
            return parser.parseSmiles(smiles);
        } catch (InvalidSmilesException e) {
            System.err.println("Invalid SMILES: " + e.getMessage());
            IAtomContainer container = new AtomContainer();
            container.addAtom(new Atom("C"));
            return container;
        }
    }
    private void calculateBruttoFormula(){
        formula = MolecularFormulaManipulator.getMolecularFormula(mol);
    }
    public String getBruttoFormula(){
        return MolecularFormulaManipulator.getString(formula);
    }

    public void printBruttoFormula(){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StructureData that)) return false;

        return getSmiles().equals(that.getSmiles());
    }

    @Override
    public int hashCode() {
        return getSmiles().hashCode();
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