package users.console_ui;

import users.core.services.ValidateUserService;

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
        if (validateUserService.execute(id, password))
            System.out.println("Login Successful!");
        else
            System.out.println("Id or Password is Incorrect!");
    }
}
