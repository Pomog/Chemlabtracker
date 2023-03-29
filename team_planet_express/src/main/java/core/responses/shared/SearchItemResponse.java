package core.responses.shared;

import core.domain.item.Item;
import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class SearchItemResponse extends CoreResponse {

    private final List<Item> items;

    //why does this have items in constructor?
    public SearchItemResponse(List<Item> items, List<CoreError> errors) {
        super(errors);
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

}
