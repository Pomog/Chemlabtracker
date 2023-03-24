package core.services.validators.customer;

import core.database.Database;
import core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListCartItemValidator {
    private static final String FIELD_NAME = "name";
    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    private final Database database;

    public ListCartItemValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(Long user_id) {
        List<CoreError> errors = new ArrayList<>();
        validateCartNotOpen(user_id).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateCartNotOpen(Long userId) {
        return (database.accessCartDatabase().findOpenCartForUserId(userId).isEmpty())
                ? Optional.of(new CoreError(FIELD_NAME, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }
}
