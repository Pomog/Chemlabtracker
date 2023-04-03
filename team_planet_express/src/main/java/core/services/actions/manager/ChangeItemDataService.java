package core.services.actions.manager;

import core.database.Database;
import core.requests.manager.ChangeItemDataRequest;
import core.responses.CoreError;
import core.responses.manager.ChangeItemDataResponse;
import core.services.validators.actions.manager.ChangeItemDataValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ChangeItemDataService {

    private final Database database;
    private final ChangeItemDataValidator validator;

    public ChangeItemDataService(Database database, ChangeItemDataValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ChangeItemDataResponse execute(ChangeItemDataRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeItemDataResponse(errors);
        }
        //TODO 大きくて、可愛くない
        Long itemId = Long.parseLong(request.getItemId());
        //TODO this is bad, but I am stupid tonight
        if (request.getNewItemName() != null && !request.getNewItemName().isBlank()) {
            database.accessItemDatabase().changeName(itemId, request.getNewItemName());
        }
        if (request.getNewPrice() != null && !request.getNewPrice().isBlank()) {
            BigDecimal newPrice = new BigDecimal(request.getNewPrice()).setScale(2, RoundingMode.HALF_UP);
            database.accessItemDatabase().changePrice(itemId, newPrice);
        }
        if (request.getNewAvailableQuantity() != null && !request.getNewAvailableQuantity().isBlank()) {
            Integer newAvailableQuantity = Integer.parseInt(request.getNewAvailableQuantity());
            database.accessItemDatabase().changeAvailableQuantity(itemId, newAvailableQuantity);
        }
        return new ChangeItemDataResponse();
    }

}
