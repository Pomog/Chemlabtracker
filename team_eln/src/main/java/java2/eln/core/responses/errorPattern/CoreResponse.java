package java2.eln.core.responses.errorPattern;

import java.util.List;

 public abstract class CoreResponse {
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
