package java2.eln.core.database;

import java2.eln.domain.ReactionData;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImplIM implements DatabaseIM {
    public List<ReactionData> reactions = new ArrayList<>();

    @Override
    public void addReaction(ReactionData reaction) {
        reactions.add(reaction);
    }

    @Override
    public boolean delReactionByCode(String code) {
        return reactions.removeIf(reactionData -> reactionData.getCode().equals(code));
    }

    @Override
    public List<ReactionData> getAllReactions() {
        return reactions;
    }

}

