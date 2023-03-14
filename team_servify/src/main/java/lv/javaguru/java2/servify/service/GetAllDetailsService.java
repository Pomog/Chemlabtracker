package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.detail_builder.Detail;
import lv.javaguru.java2.servify.database.Database;

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
