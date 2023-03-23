package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.responses.customer.BuyResponse;
import core.responses.customer.CoreError;
import core.services.cart.CartValidator;
import core.services.validators.customer.BuyValidator;
import core.support.MutableLong;

import java.time.LocalDate;
import java.util.List;

public class BuyService {

    private final Database database;
    private final MutableLong currentUserId;

    private final BuyValidator validator;

    public BuyService(Database database, BuyValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.validator = validator;
    }

    public BuyResponse execute() {
        List<CoreError> errors = validator.validate(currentUserId.getValue());
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = new CartValidator().getOpenCartForUserId(database.accessCartDatabase(), currentUserId.getValue());
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
        return new BuyResponse();
    }

}
