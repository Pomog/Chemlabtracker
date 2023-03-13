package database;

import domain.ReactionData;

import java.util.List;


public interface DatabaseIM {

    void addReaction(ReactionData reaction);

    void delReactionByCode(String code);

    List<ReactionData> getAllReactions();
}
