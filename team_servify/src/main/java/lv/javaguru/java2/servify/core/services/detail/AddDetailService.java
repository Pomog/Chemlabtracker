package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.Database;

public class AddDetailService {

    private Database database;

    public AddDetailService(Database database){
        this.database = database;
    }

    public void execute(Detail detail) {
        database.save(detail);
    }

}
