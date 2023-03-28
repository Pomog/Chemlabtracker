package core.services.actions.shared;

import core.database.Database;
import core.domain.item.Item;
import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;
import core.responses.shared.SearchItemResponse;
import core.services.validators.shared.SearchItemValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SearchItemService {
    private final Database database;
    private final SearchItemValidator validator;

    public SearchItemService(Database database, SearchItemValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchItemResponse(null, errors);
        }
        List<Item> items;
        if (!(request.getItemName() == null) && isPresent(request.getPrice())) {
            items = database.accessItemDatabase().SearchByName(request.getItemName());
        } else if (!(request.getItemName() == null) && !isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = database.accessItemDatabase().SearchByNameAndPrice(request.getItemName(), price);
        } else {
            items = database.accessItemDatabase().getAllItems();
        }
        return new SearchItemResponse(items, null);
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }
}
