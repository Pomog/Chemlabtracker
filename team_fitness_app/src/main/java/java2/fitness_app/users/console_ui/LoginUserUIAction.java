package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.LoginUserResponse;
import java2.fitness_app.users.core.services.LoginUserService;

import java.util.Scanner;

public class LoginUserUIAction implements UIAction {

    private LoginUserService loginUserService;

    public LoginUserUIAction(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LoginUserRequest request = new LoginUserRequest(id, password);
        LoginUserResponse response = loginUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserLogged()) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Id or Password is Incorrect!");
            }
        }
    }

}
