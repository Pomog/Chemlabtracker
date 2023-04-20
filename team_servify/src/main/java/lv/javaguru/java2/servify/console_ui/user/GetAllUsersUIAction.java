package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.core.services.user.GetAllUsersService;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

@DIComponent
public class GetAllUsersUIAction implements UIAction {
    @DIDependency private GetAllUsersService getAllUsersService;

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersResponse response = getAllUsersService.execute();
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }
}
