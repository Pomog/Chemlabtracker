package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.LoginUserRequest;
import java2.fitness_app.users.core.responses.LoginUserResponse;
import java2.fitness_app.users.core.services.LoginUserService;
import java2.fitness_app.dependency_injection.DIComponent;
import java2.fitness_app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginUserUIAction implements UIAction {

    @Autowired private LoginUserService loginUserService;


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
