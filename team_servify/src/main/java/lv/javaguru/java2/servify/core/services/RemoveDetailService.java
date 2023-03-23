package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.Database;

public class RemoveDetailService {

    private Database database;

    public RemoveDetailService(Database database) {
        this.database = database;
    }

    public void execute(Long id) {
        database.deleteById(id);
    }

}
