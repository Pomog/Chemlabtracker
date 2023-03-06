public class DemoForStructureData {
    public static void main(String[] args) {

        // Testing StructureData class

        StructureData demoStructure = new StructureData("CC(=O)O", "Acetic acid");
        System.out.println(demoStructure.getName());
        demoStructure.getBruttoFormula();
        demoStructure.getMW();
        System.out.println("");
        StructureData demoStructure2 = new StructureData("c1ncccc1[C@@H]2CCCN2C", "Nicotine");
        System.out.println(demoStructure2.getName());
        demoStructure2.getBruttoFormula();
        demoStructure2.getMW();

        // Testing ReactionData class

        ReactionData demoReactionLog = new ReactionData("TP1", "The Friedel-Crafts acylation");

        CreateStructureFromFile newMaterial = new CreateStructureFromFile("team_eln/src/data/demoReaction1.txt");
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM1"));
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM2"));
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM3"));
        demoReactionLog.setMainProduct(newMaterial.readFromFile("MP"));

        CreateConditionDataFromFile newConditions = new CreateConditionDataFromFile("team_eln/src/data/demoReaction1.txt");
        demoReactionLog.setConditions(newConditions.readFromFile());

        System.out.println("Reaction \n" +  demoReactionLog + "\n Reaction log end.");

    }
}
