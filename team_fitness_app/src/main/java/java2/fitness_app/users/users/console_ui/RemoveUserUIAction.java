package java2.fitness_app.users.users.console_ui;

import java2.fitness_app.users.users.core.services.RemoveUserService;

import java.util.Scanner;

public class RemoveUserUIAction implements UIAction{

    private RemoveUserService removeUserService;

    public RemoveUserUIAction(RemoveUserService removeUserService) {
        this.removeUserService = removeUserService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (removeUserService.execute(id, password))
            System.out.println("User was removed from user database.");
        else
            System.out.println("Id or Password is Incorrect!");
    }
}