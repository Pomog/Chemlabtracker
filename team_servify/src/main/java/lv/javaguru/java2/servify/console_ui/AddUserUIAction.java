package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.core.requests.AddUserRequest;
import lv.javaguru.java2.servify.core.responses.AddUserResponse;
import lv.javaguru.java2.servify.core.services.AddUserService;

import java.util.Scanner;

public class AddUserUIAction implements UIAction {
    private AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your First Name");
        String firstName = input.nextLine();
        System.out.println("Enter your Second Name");
        String secondName = input.nextLine();
        System.out.println("Enter your e-mail");
        String email = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.nextLine();

        AddUserRequest request = new AddUserRequest(firstName, secondName, email, phoneNumber);
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
