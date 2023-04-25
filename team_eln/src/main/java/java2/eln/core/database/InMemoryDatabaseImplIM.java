package java2.eln.core.database;

import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;
import java2.eln.domain.ReactionData;

import java.util.ArrayList;
import java.util.List;

@DIComponent
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

    @Override
    public boolean hasReactionWithCode(String reactionCode) {
        return reactions.stream()
                .anyMatch(reaction -> reaction.getCode().equals(reactionCode));
    }


}

