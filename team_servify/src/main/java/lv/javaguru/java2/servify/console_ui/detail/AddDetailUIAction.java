package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.detail_builder.DetailChoice;
import lv.javaguru.java2.servify.core.services.detail.AddDetailService;

public class AddDetailUIAction implements UIAction {

    private AddDetailService addDetailService;

    public AddDetailUIAction(AddDetailService addDetailService) {
        this.addDetailService = addDetailService;
    }

    @Override
    public void execute() {
        addDetailService.execute(DetailChoice.newDetail());
        System.out.println("Your detail was added to list.");
    }
}
