package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.detail_builder.DetailChoice;
import lv.javaguru.java2.servify.core.services.detail.AddDetailService;

@DIComponent
public class AddDetailUIAction implements UIAction {

    @DIDependency private AddDetailService addDetailService;

    @Override
    public void execute() {
        addDetailService.execute(DetailChoice.newDetail());
        System.out.println("Your detail was added to list.");
    }
}
