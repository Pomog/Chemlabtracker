package java2.eln.core.database;

import java2.eln.core.domain.ReactionData;

import java.util.*;


/**
 * This interface represents a database that stores reaction data.
 */
public interface DatabaseIM {

    /**
     * Adds the specified reaction to the database.
     *
     * @param reaction the reaction to add to the database
     */
    void addReaction(ReactionData reaction);

    /**
     * Deletes the reaction with the specified code from the database.
     *
     * @param code the code of the reaction to delete
     * @return true if the reaction was deleted successfully, false otherwise
     */
    boolean delReactionByCode(String code);

    /**
     * Returns a list of all reactions stored in the database.
     *
     * @return a list of all reactions stored in the database
     */
    List<ReactionData> getAllReactions();

    /**
     * Returns true if a reaction with the specified code is stored in the database, false otherwise.
     *
     * @param reactionCode the code of the reaction to check for
     * @return true if a reaction with the specified code is stored in the database, false otherwise
     */
    boolean hasReactionWithCode(String reactionCode);


}

