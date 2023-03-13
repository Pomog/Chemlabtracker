package services;

import database.DatabaseIM;
import domain.ReactionData;

public class AddReactionService {
    private DatabaseIM databaseIM;

    public AddReactionService(DatabaseIM databaseIM) {
        this.databaseIM = databaseIM;
    }

     public void execute(String code, String name, String filename) {
        ReactionData demoReactionLog = new ReactionData(code, name);
        additionOfMaterials(filename, demoReactionLog); // The Materials added to the ReactionData from the file
        additionOfConditions(filename, demoReactionLog); // The Reaction Conditions added to the ReactionData from the file

        databaseIM.addReaction(demoReactionLog);
        consoleMessage(demoReactionLog);
    }

    private void additionOfConditions(String filename, ReactionData demoReactionLog) {
        CreateConditionDataFromFile newConditions = new CreateConditionDataFromFile(filename);
        demoReactionLog.setConditions(newConditions.readFromFile());
    }

    // try regex !!!
    private void additionOfMaterials(String filename, ReactionData demoReactionLog) {
        CreateStructureFromFile newMaterial = new CreateStructureFromFile(filename);
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM1")); // starting material
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM2"));
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM3"));
        demoReactionLog.setMainProduct(newMaterial.readFromFile("MP")); // main product of the reaction
    }

    private void consoleMessage(ReactionData addedReaction) {
        System.out.println("baseClasses.ReactionData object with code " + addedReaction.getCode() + " has been successfully added.");
    }

}
