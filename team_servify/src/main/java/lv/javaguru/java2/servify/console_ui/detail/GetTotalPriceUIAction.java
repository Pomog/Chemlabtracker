package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetTotalPriceService;

public class GetTotalPriceUIAction implements UIAction {

    private GetTotalPriceService getTotalPriceService;

    public GetTotalPriceUIAction(GetTotalPriceService getTotalPriceService) {
        this.getTotalPriceService = getTotalPriceService;
    }

    @Override
    public void execute() {
        System.out.println("Total price: " + getTotalPriceService.execute() + " EUR");
    }

}
