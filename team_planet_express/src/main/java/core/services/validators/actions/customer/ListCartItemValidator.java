package core.services.validators.actions.customer;

import core.requests.customer.ListCartItemsRequest;
import core.responses.CoreError;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.MutableLongUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class ListCartItemValidator {

    private final MutableLongUserIdValidator userIdValidator;
    private final CartValidator cartValidator;

    public ListCartItemValidator(MutableLongUserIdValidator userIdValidator, CartValidator cartValidator) {
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateMutableLongUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        return errors;
    }

}
