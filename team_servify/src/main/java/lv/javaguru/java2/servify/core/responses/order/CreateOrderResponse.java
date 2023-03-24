package lv.javaguru.java2.servify.core.responses.order;

import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.CoreResponse;
import lv.javaguru.java2.servify.domain.Order;

import java.util.List;

public class CreateOrderResponse extends CoreResponse {

    private Order newOrder;

    public CreateOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public CreateOrderResponse(Order newOrder) {
        this.newOrder = newOrder;
    }

    public Order getNewOrder() {
        return newOrder;
    }
}
