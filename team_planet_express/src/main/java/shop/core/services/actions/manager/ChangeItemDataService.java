package shop.core.services.actions.manager;

import shop.core.database.Database;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.services.validators.actions.manager.ChangeItemDataValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@DIComponent
public class ChangeItemDataService {

    @DIDependency
    private Database database;
    @DIDependency
    private ChangeItemDataValidator validator;

    public ChangeItemDataResponse execute(ChangeItemDataRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeItemDataResponse(errors);
        }
        Long itemId = Long.parseLong(request.getItemId());
        changeItemName(itemId, request.getNewItemName());
        changePrice(itemId, request.getNewPrice());
        changeAvailableQuantity(itemId, request.getNewAvailableQuantity());
        return new ChangeItemDataResponse();
    }

    private void changeItemName(Long itemId, String newItemName) {
        if (newItemName != null && !newItemName.isBlank()) {
            database.accessItemDatabase().changeName(itemId, newItemName);
        }
    }

    private void changePrice(Long itemId, String newPriceString) {
        if (newPriceString != null && !newPriceString.isBlank()) {
            BigDecimal newPrice = new BigDecimal(newPriceString).setScale(2, RoundingMode.HALF_UP);
            database.accessItemDatabase().changePrice(itemId, newPrice);
        }
    }

    private void changeAvailableQuantity(Long itemId, String newAvailableQuantityString) {
        if (newAvailableQuantityString != null && !newAvailableQuantityString.isBlank()) {
            Integer newAvailableQuantity = Integer.parseInt(newAvailableQuantityString);
            database.accessItemDatabase().changeAvailableQuantity(itemId, newAvailableQuantity);
        }
    }

}
