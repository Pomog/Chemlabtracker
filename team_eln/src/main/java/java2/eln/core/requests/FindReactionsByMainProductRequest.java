package java2.eln.core.requests;

import java2.eln.core.domain.StructureData;

public class FindReactionsByMainProductRequest {
    private final StructureData searchedSubstance;

    public FindReactionsByMainProductRequest(StructureData searchedSubstance) {
        this.searchedSubstance = searchedSubstance;
    }

    public StructureData getSearchedSubstance() {
        return searchedSubstance;
    }
}
