package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.database.Database;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.domain.PriceList;

import java.math.BigDecimal;
import java.util.List;

@DIComponent
public class GetTotalPriceService {

    @DIDependency private Database database;

    public BigDecimal execute() {
        PriceList data = new PriceList();
        List<Detail> listWithPrices = data.getDetailPricesList();
        return database.getTotalPrice(listWithPrices);
    }

}
