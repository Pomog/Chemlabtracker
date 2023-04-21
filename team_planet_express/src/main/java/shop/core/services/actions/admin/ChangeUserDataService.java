package shop.core.services.actions.admin;

import shop.core.database.Database;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ChangeUserDataService {

    @DIDependency
    private Database database;

    public void execute() {
        //TODO change user data
    }

}
