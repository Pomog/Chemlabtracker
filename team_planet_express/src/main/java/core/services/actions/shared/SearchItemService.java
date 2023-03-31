package core.services.actions.shared;

import core.database.Database;
import core.domain.item.Item;
import core.requests.shared.Ordering;
import core.requests.shared.SearchItemRequest;
import core.responses.CoreError;
import core.responses.shared.SearchItemResponse;
import core.services.validators.shared.SearchItemValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SearchItemService {
    private final Database database;
    private final SearchItemValidator validator;

    public SearchItemService(Database database, SearchItemValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(null, errors);
        } else {
            List<Item> items = search(request);
            items = order(items, request.getOrderings());
            response = new SearchItemResponse(items, null);
        }
        // only one return POG
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;

        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = database.accessItemDatabase().searchByName(request.getItemName());
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = database.accessItemDatabase().searchByNameAndPrice(request.getItemName(), price);
        } else {
            items = database.accessItemDatabase().getAllItems();
        }
        return items;
    }

    private List<Item> order(List<Item> items, List<Ordering> orderings) {
        if (orderings != null) {
            Stream<Item> itemStream = items.stream();
            Optional<Ordering> orderingByPriceOptional = orderings.stream().
                    filter(ordering -> ordering.getOrderBy().equals("price")).findFirst();
            if (orderingByPriceOptional.isPresent()) {
                itemStream = itemStream.sorted(
                        orderingByPriceOptional.get().getOrderDirection().equals("reverse") ?
                                Comparator.comparing(Item::getPrice).reversed() :
                                Comparator.comparing(Item::getPrice)
                );
            }
            Optional<Ordering> orderingByItemsOptional = orderings.stream().filter(ordering -> ordering.getOrderBy().equals("item")).findFirst();
            if (orderingByItemsOptional.isPresent()) {
                itemStream = itemStream.sorted(
                        orderingByItemsOptional.get().getOrderDirection().equals("reverse") ?
                                Comparator.comparing(Item::getName).reversed() :
                                Comparator.comparing(Item::getName)
                );
            }
            items = itemStream.toList();
        }
        return items;
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }
}
