package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.requests.user.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.responses.user.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.core.services.user.SetUserNotActiveService;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SetUserNotActiveUIAction implements UIAction {
    @Autowired private SetUserNotActiveService setUserNotActiveService;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter User id to remove: ");
        Long userId = Long.parseLong(input.nextLine());
        SetUserNotActiveRequest request = new SetUserNotActiveRequest(userId);
        SetUserNotActiveResponse response = setUserNotActiveService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserInactivated()) {
                System.out.println("User is deactivated.");
            } else {
                System.out.println("User is not deactivated.");
            }
        }
    }
}
