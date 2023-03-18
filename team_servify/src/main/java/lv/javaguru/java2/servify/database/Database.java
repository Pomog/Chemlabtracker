package lv.javaguru.java2.servify.database;

import lv.javaguru.java2.servify.domain.Detail;

import java.math.BigDecimal;
import java.util.List;

public interface Database {

    void save(Detail detail);

    void deleteById(Long id);

    List<Detail> getAllDetails();

    BigDecimal getTotalPrice(List<Detail> listWithPrices);

}
