package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

@DIComponent
public class GetAllDetailsUIAction implements UIAction {

    @DIDependency private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        System.out.println("Detail list: ");
        getAllDetailsService.execute().forEach(System.out::println);
        System.out.println("Detail list end.");
    }
}
