package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.requests.customer.BuyRequest;
import core.responses.CoreError;
import core.responses.customer.BuyResponse;
import core.services.validators.customer.BuyValidator;
import core.support.MutableLong;

import java.time.LocalDate;
import java.util.List;

public class BuyService {

    private final Database database;
    private final BuyValidator validator;
    private final MutableLong currentUserId;

    public BuyService(Database database, BuyValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.validator = validator;
        this.currentUserId = currentUserId;
    }

    public MutableLong getCurrentUserId() {
        return currentUserId;
    }

    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
        return new BuyResponse();
    }

}
