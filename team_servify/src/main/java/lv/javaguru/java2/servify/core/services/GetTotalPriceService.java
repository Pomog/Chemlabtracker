package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.Database;
import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.domain.PriceList;

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
