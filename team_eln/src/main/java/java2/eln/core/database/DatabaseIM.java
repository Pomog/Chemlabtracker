package java2.eln.core.database;

import java2.eln.domain.ReactionData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


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

    /**
     * Returns a sorted list of all reactions stored in the database, using the specified comparator.
     *
     * @param comparator the comparator to use for sorting the reactions
     * @return a sorted list of all reactions stored in the database
     */
    default List<ReactionData> sortReactions(Comparator<ReactionData> comparator) {
        List<ReactionData> sortedList = new ArrayList<>(getAllReactions());
        sortedList.sort(comparator);
        return sortedList;
    }

    /**
     * Returns a sorted list of the specified reactions, using the specified comparator.
     *
     * @param reactions  the reactions to sort
     * @param comparator the comparator to use for sorting the reactions
     * @return a sorted list of the specified reactions
     */
    default List<ReactionData> sortReactions(List<ReactionData> reactions, Comparator<ReactionData> comparator) {
        List<ReactionData> sortedList = new ArrayList<>(reactions);
        sortedList.sort(comparator);
        return sortedList;
    }
}

