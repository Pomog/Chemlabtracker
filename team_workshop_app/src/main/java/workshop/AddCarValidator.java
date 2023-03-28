package workshop;

import workshop.core.requests.AddCarRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddCarValidator {
    public List<CoreError> validate(AddCarRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateBrand(request).ifPresent(errors::add);
        validateModel(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateBrand(AddCarRequest request) {
        return (request.getBrand() == null || request.getBrand().isEmpty())
                ? Optional.of(new CoreError("brand", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateModel(AddCarRequest request) {
        return (request.getModel() == null || request.getModel().isEmpty())
                ? Optional.of(new CoreError("model", "Must not be empty!"))
                : Optional.empty();
    }

}
