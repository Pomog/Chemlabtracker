package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.Database;

@DIComponent
public class AddDetailService {

    @DIDependency private Database database;

    public void execute(Detail detail) {
        database.save(detail);
    }

}
