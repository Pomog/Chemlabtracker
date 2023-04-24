package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.detail_builder.DetailChoice;
import lv.javaguru.java2.servify.core.services.detail.AddDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDetailUIAction implements UIAction {

    @Autowired private AddDetailService addDetailService;

    @Override
    public void execute() {
        addDetailService.execute(DetailChoice.newDetail());
        System.out.println("Your detail was added to list.");
    }
}
