package workshop.core.services;

import workshop.core.requests.RemoveCarRequest;
import workshop.core.responses.RemoveCarResponse;
import workshop.core.database.Database;


public class RemoveCarService {
    Database database;

    public RemoveCarService(Database database) {
        this.database = database;
    }

    public RemoveCarResponse execute(RemoveCarRequest request){
      boolean isCarRemoved = database.deleteById(request.getCarIdToRemove());
      return new RemoveCarResponse(isCarRemoved);

    }
}
