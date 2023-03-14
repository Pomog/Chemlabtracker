package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.Database;
import lv.javaguru.java2.servify.detail_builder.Detail;
import lv.javaguru.java2.servify.detail_builder.PriceList;

import java.math.BigDecimal;
import java.util.List;

public class GetTotalPriceService {

    private Database database;

    public GetTotalPriceService(Database database) {
        this.database = database;
    }

    public BigDecimal execute() {
        PriceList data = new PriceList();
        List<Detail> listWithPrices = data.getDetailPricesList();
        return database.getTotalPrice(listWithPrices);
    }

}
