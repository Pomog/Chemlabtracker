package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.AddUserRequest;
import java2.fitness_app.users.core.responses.AddUserResponse;
import java2.fitness_app.users.core.services.AddUserService;
import java2.fitness_app.dependency_injection.DIComponent;
import java2.fitness_app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {

    @Autowired private AddUserService addUserService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        AddUserRequest request = new AddUserRequest(username, password);
        AddUserResponse response = addUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New user id is: " + response.getNewUser().getId());
            System.out.println("You are successfully added!");
        }
    }

}
