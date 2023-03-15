package java2.eln.database;

import java2.eln.domain.ReactionData;

import java.util.List;


public interface DatabaseIM {

    void addReaction(ReactionData reaction);

    void delReactionByCode(String code);

    List<ReactionData> getAllReactions();
}
