package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.Detail;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryDatabaseImpl implements Database {

    private Long nextId = 1L;
    private List<Detail> details = new ArrayList<>();

    @Override
    public void save(Detail detail) {
        detail.setId(nextId);
        nextId++;
        details.add(detail);
    }

    @Override
    public void deleteById(Long id) {
        details.stream()
                .filter(detail -> detail.getId().equals(id))
                .findFirst()
                .ifPresent(detail -> details.remove(detail));
    }

    @Override
    public List<Detail> getAllDetails() {
        return details;
    }

    @Override
    public BigDecimal getTotalPrice(List<Detail> listWithPrices) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Detail detail : listWithPrices) {
            if (details.contains(detail)) {
                totalPrice = totalPrice.add(detail.getPrice());
            }
        }
        return totalPrice;
    }
}
