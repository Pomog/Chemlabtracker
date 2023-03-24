package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.Database;

import java.util.List;

public class GetAllDetailsService {

    private Database database;

    public GetAllDetailsService(Database database) {
        this.database = database;
    }

    public List<Detail> execute() {
        return database.getAllDetails();
    }

}
