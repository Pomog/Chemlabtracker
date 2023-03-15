package database;

import domain.ReactionData;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImplIM implements DatabaseIM {
    public List<ReactionData> reactions = new ArrayList<>();

    @Override
    public void addReaction(ReactionData reaction) {
        reactions.add(reaction);
    }

    @Override
    public void delReactionByCode(String code) {
        boolean removed = reactions.removeIf(reactionData -> reactionData.getCode().equals(code));
        consoleMessage(code, removed);

    }

    @Override
    public List<ReactionData> getAllReactions() {
        return reactions;
    }

    private static void consoleMessage(String code, boolean removed) {
        if (removed) {
            System.out.println("baseClasses.ReactionData object with code " + code + " has been successfully removed from the list.");
        } else {
            System.out.println("baseClasses.ReactionData object with code " + code + " was not found in the list.");
        }
    }
}

