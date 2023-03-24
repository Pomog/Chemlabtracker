package lv.javaguru.java2.servify.core.requests.order;

import lv.javaguru.java2.servify.domain.Detail;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class CreateOrderRequest {

    private List<Detail> detailList;

    private Date orderDate;

    public CreateOrderRequest(List<Detail> detailList) {
        this.detailList = detailList;
        this.orderDate = Date.from(Instant.now());
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
