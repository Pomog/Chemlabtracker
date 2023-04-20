package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetTotalPriceService;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

@DIComponent
public class GetTotalPriceUIAction implements UIAction {

    @DIDependency private GetTotalPriceService getTotalPriceService;

    @Override
    public void execute() {
        System.out.println("Total price: " + getTotalPriceService.execute() + " EUR");
    }

}
