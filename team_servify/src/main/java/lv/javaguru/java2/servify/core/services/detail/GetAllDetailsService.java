package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.Database;

import java.util.List;

@DIComponent
public class GetAllDetailsService {

    @DIDependency private Database database;

    public List<Detail> execute() {
        return database.getAllDetails();
    }

}
