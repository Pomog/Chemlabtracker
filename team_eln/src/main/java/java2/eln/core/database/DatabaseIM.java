package java2.eln.core.database;

import java2.eln.domain.ReactionData;

import java.util.List;


public interface DatabaseIM {

    void addReaction(ReactionData reaction);

    boolean delReactionByCode(String code);

    List<ReactionData> getAllReactions();
}
