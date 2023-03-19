package java2.eln.core.responses;

import java.util.List;

abstract class CoreResponse {
    private List<CoreError> errors;

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public CoreResponse() {
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
