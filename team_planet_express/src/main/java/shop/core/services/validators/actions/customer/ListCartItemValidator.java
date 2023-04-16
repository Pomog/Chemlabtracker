package shop.core.services.validators.actions.customer;

import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class ListCartItemValidator {

    private final CurrentUserIdValidator userIdValidator;
    private final CartValidator cartValidator;

    public ListCartItemValidator(CurrentUserIdValidator userIdValidator, CartValidator cartValidator) {
        this.userIdValidator = userIdValidator;
        this.cartValidator = cartValidator;
    }

    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        return errors;
    }

}
