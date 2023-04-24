package shop.core.services.validators.actions.customer;

import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class ListCartItemValidator {

    @DIDependency
    private CurrentUserIdValidator userIdValidator;
    @DIDependency
    private CartValidator cartValidator;


    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        return errors;
    }

}
