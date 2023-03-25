package java2.fitness_app.users.users.console_ui;

import java2.fitness_app.users.users.core.requests.ValidateUserRequest;
import java2.fitness_app.users.users.core.responses.ValidateUserResponse;
import java2.fitness_app.users.users.core.services.ValidateUserService;

import java.util.Scanner;

public class ValidateUserUIAction implements UIAction {

    private ValidateUserService validateUserService;

    public ValidateUserUIAction(ValidateUserService validateUserService) {
        this.validateUserService = validateUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        ValidateUserRequest request = new ValidateUserRequest(id, password);
        ValidateUserResponse response = validateUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserValidated()) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Id or Password is Incorrect!");
            }
        }
    }

}
