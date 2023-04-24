package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllDetailsUIAction implements UIAction {

    @Autowired private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        System.out.println("Detail list: ");
        getAllDetailsService.execute().forEach(System.out::println);
        System.out.println("Detail list end.");
    }
}
