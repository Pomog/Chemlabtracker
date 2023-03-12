package database;

import baseClasses.ReactionData;

import java.util.List;


public interface Database {

    void addReaction(ReactionData reaction);

    void delReactionByCode(String code);

    List<ReactionData> getAllReactions();
}
