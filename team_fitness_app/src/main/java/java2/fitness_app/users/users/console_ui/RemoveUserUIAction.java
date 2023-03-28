package java2.fitness_app.users.users.console_ui;

import java2.fitness_app.users.users.core.requests.RemoveUserRequest;
import java2.fitness_app.users.users.core.responses.RemoveUserResponse;
import java2.fitness_app.users.users.core.services.RemoveUserService;

import java.util.Scanner;

public class RemoveUserUIAction implements UIAction {

    private RemoveUserService removeUserService;

    public RemoveUserUIAction(RemoveUserService removeUserService) {
        this.removeUserService = removeUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");

        String id = scanner.nextLine();
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
