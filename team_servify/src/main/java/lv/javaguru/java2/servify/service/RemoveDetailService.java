package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.Database;

public class RemoveDetailService {

    private Database database;

    public RemoveDetailService(Database database) {
        this.database = database;
    }

    public void execute(Long id) {
        database.deleteById(id);
    }

}
