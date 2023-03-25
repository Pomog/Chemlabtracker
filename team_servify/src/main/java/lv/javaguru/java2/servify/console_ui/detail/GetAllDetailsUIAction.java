package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;

public class GetAllDetailsUIAction implements UIAction {

    private GetAllDetailsService getAllDetailsService;

    public GetAllDetailsUIAction(GetAllDetailsService getAllDetailsService) {
        this.getAllDetailsService = getAllDetailsService;
    }

    @Override
    public void execute() {
        System.out.println("Detail list: ");
        getAllDetailsService.execute().forEach(System.out::println);
        System.out.println("Detail list end.");
    }
}
