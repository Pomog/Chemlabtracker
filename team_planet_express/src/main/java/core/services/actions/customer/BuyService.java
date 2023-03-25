package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.responses.CoreError;
import core.responses.customer.BuyResponse;
import core.services.cart.CartValidator;
import core.services.validators.customer.BuyValidator;
import core.support.MutableLong;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BuyService {

    private final Database database;
    private final MutableLong currentUserId;

    private final BuyValidator validator;
    private final CartValidator cartValidator;

    public BuyService(Database database, BuyValidator validator, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.validator = validator;
        this.cartValidator = new CartValidator(database);
    }

    public BuyResponse execute() {
        //TODO now it is huge an ugly
        Optional<CoreError> error = cartValidator.validateOpenCartExistsForUserId(currentUserId.getValue());
        if (error.isPresent()) {
            return new BuyResponse(List.of(error.get()));
        }
        List<CoreError> errors = validator.validate(currentUserId.getValue());
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue()).get();
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
        return new BuyResponse();
    }

}
