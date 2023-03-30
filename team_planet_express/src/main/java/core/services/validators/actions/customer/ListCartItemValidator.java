package core.services.validators.actions.customer;

import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.LongUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class ListCartItemValidator {

    private final LongUserIdValidator userIdValidator;
    private final CartValidator cartValidator;

    public ListCartItemValidator(LongUserIdValidator userIdValidator, CartValidator cartValidator) {
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateLongUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        return errors;
    }

}
