package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.core.services.user.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUIAction implements UIAction {
    @Autowired private GetAllUsersService getAllUsersService;

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersResponse response = getAllUsersService.execute();
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }
}
