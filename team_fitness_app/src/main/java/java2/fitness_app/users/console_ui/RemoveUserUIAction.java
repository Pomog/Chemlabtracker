package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.core.responses.RemoveUserResponse;
import java2.fitness_app.users.core.services.RemoveUserService;
import java2.fitness_app.users.dependency_injection.DIComponent;
import java2.fitness_app.users.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class RemoveUserUIAction implements UIAction {

    @DIDependency private RemoveUserService removeUserService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        RemoveUserRequest request = new RemoveUserRequest(id, password);
        RemoveUserResponse response = removeUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserRemoved()) {
                System.out.println("User removed successfully");
            } else {
                System.out.println("User was not removed");
            }
        }
    }
}
