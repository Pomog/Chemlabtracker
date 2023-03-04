public class DemoForStructureData {
    public static void main(String[] args) {

        StructureData demoStructure = new StructureData("CC(=O)O", "Acetic acid");
        System.out.println(demoStructure.getName());
        demoStructure.getBruttoFormula();
        demoStructure.getMW();
                System.out.println("");
        StructureData demoStructure2 = new StructureData("c1ncccc1[C@@H]2CCCN2C", "Nicotine");
        System.out.println(demoStructure2.getName());
        demoStructure2.getBruttoFormula();
        demoStructure2.getMW();
    }
}
