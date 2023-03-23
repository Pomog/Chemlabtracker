package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.core.services.GetTotalPriceService;

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
