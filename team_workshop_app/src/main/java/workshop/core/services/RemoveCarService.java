package workshop.core.services;

import workshop.core.responses.CoreError;
import workshop.core.requests.RemoveCarRequest;
import workshop.core.responses.RemoveCarResponse;
import workshop.core.database.Database;

import java.util.ArrayList;
import java.util.List;


public class RemoveCarService {
    private Database database;

    public RemoveCarService(Database database) {
        this.database = database;
    }

    public RemoveCarResponse execute(RemoveCarRequest request){
        if (request.getCarIdToRemove() == null) {
            CoreError error = new CoreError("id", "Must not be empty!");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveCarResponse(errors);
        }
      boolean isCarRemoved = database.deleteById(request.getCarIdToRemove());
      return new RemoveCarResponse(isCarRemoved);

    }
}
