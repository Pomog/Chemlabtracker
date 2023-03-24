package lv.javaguru.java2.servify.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private Long userId; // so user id or client id? Only client makes requests. Manager just approves...
    private List<Detail> detailList;
    private Date orderDate;
    private Date systemResponseDate;
    private Date managerResponseDate; // do we need this? Or just one final interaction date?
    private BigDecimal priceInitial; // gen by system
    private BigDecimal priceManager; // overriden by manager
    private BigDecimal priceFinal;
    private OrderStatus orderStatus;
    private BigDecimal price;
    private String notes;
}
