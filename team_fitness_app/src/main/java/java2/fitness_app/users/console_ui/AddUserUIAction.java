package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.AddUserRequest;
import java2.fitness_app.users.core.responses.AddUserResponse;
import java2.fitness_app.users.core.services.AddUserService;
import java2.fitness_app.users.dependency_injection.DIComponent;
import java2.fitness_app.users.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddUserUIAction implements UIAction {

    @DIDependency private AddUserService addUserService;


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
