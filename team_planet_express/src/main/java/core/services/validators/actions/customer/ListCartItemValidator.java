package core.services.validators.actions.customer;

import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.services.validators.cart.CartValidator;

import java.util.ArrayList;
import java.util.List;

public class ListCartItemValidator {

    private final CartValidator cartValidator;

    public ListCartItemValidator(CartValidator cartValidator) {
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(ListCartItemsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        //TODO validate id
        cartValidator.validateOpenCartExistsForUserId(request.getUserId()).ifPresent(errors::add);
        return errors;
    }

}
