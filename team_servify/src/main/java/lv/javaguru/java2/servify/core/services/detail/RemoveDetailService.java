package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.database.Database;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

@DIComponent
public class RemoveDetailService {

    @DIDependency private Database database;

    public void execute(Long id) {
        database.deleteById(id);
    }

}
