package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.database.Database;
import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.domain.PriceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class GetTotalPriceService {

    @Autowired private Database database;

    public BigDecimal execute() {
        PriceList data = new PriceList();
        List<Detail> listWithPrices = data.getDetailPricesList();
        return database.getTotalPrice(listWithPrices);
    }

}
