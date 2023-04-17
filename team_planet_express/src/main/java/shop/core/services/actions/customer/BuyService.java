package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.time.LocalDate;
import java.util.List;

public class BuyService {

    private final Database database;
    private final BuyValidator validator;
    private final DatabaseAccessValidator databaseAccessValidator;

    public BuyService(Database database, BuyValidator validator, DatabaseAccessValidator databaseAccessValidator) {
        this.database = database;
        this.validator = validator;
        this.databaseAccessValidator = databaseAccessValidator;
    }

    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
        return new BuyResponse();
    }

}
