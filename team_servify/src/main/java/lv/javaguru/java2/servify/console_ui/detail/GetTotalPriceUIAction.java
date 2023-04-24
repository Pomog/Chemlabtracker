package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetTotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTotalPriceUIAction implements UIAction {

    @Autowired private GetTotalPriceService getTotalPriceService;

    @Override
    public void execute() {
        System.out.println("Total price: " + getTotalPriceService.execute() + " EUR");
    }

}
