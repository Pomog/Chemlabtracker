package java2.eln.core.database;

import java2.eln.core.domain.ReactionData;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
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

    /**
     * Sorts the list of reaction data using the specified comparator.
     *
     * @param comparator the comparator to use for sorting the list
     * @return the sorted list of reaction data
     * @throws NullPointerException if the specified comparator is null
     */
    public List<ReactionData> sortReactions(Comparator<ReactionData> comparator) {
        requireNonNullComparator(comparator);
        List<ReactionData> reactions = Objects.requireNonNullElse(getAllReactions(), Collections.emptyList());
        if (reactions.isEmpty()) {
            return reactions;
        }
        List<ReactionData> sortedList = new ArrayList<>(reactions);
        sortedList.sort(comparator);
        return sortedList;
    }

    /**
     * Returns a sorted list of the specified reactions, using the specified comparator.
     *
     * @param reactions  the reactions to sort
     * @param comparator the comparator to use for sorting the reactions
     * @return a sorted list of the specified reactions
     * @throws IllegalArgumentException if the reactions list is null
     */
    public List<ReactionData> sortReactions(List<ReactionData> reactions, Comparator<ReactionData> comparator) {
        Objects.requireNonNull(reactions, "Reactions list must not be null");
        List<ReactionData> sortedList = new ArrayList<>(reactions);
        sortedList.sort(comparator);
        return sortedList;
    }

    /**
     * Returns the specified value if it is non-null, or returns the default value
     * if the specified value is null.
     *
     * @param value the value to check for null
     * @param defaultValue the default value to return if value is null
     * @return value if it is non-null, or defaultValue if value is null
     */
    private static <T> T requireNonNullElse(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * Checks if the specified comparator is null.
     *
     * @param comparator the comparator to check for null
     * @throws NullPointerException if the specified comparator is null
     */
    private static void requireNonNullComparator(Comparator<ReactionData> comparator) {
        Objects.requireNonNull(comparator, "Comparator must not be null");
    }

}

