package core.services.actions.manager;

import core.database.Database;
import core.domain.item.Item;
import core.requests.manager.AddItemToShopRequest;
import core.responses.CoreError;
import core.responses.manager.AddItemToShopResponse;
import core.services.validators.manager.AddItemToShopValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AddItemToShopService {

    private final Database database;
    private final AddItemToShopValidator validator;

    public AddItemToShopService(Database database, AddItemToShopValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddItemToShopResponse execute(AddItemToShopRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToShopResponse(errors);
        }
        String itemName = request.getItemName();
        BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
        Integer availableQuantity = Integer.parseInt(request.getAvailableQuantity());
        database.accessItemDatabase().save(new Item(itemName, price, availableQuantity));
        return new AddItemToShopResponse();
    }

}
