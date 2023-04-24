package shop.core.services.actions.manager;

import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.validators.actions.manager.AddItemToShopValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@DIComponent
public class AddItemToShopService {

    @DIDependency
    private Database database;
    @DIDependency
    private AddItemToShopValidator validator;


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
