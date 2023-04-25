package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.requests.user.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.user.AddUserResponse;
import lv.javaguru.java2.servify.core.services.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {
    @Autowired private AddUserService addUserService;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your First Name");
        String firstName = input.nextLine();
        System.out.println("Enter your Last Name");
        String lastName = input.nextLine();
        System.out.println("Enter your e-mail");
        String email = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.nextLine();

        AddUserRequest request = new AddUserRequest(firstName, lastName, email, phoneNumber);
        AddUserResponse response = addUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New user id was: " + response.getNewUser().getId());
            System.out.println("Congratulations! New user is registered.");
        }
    }
}
