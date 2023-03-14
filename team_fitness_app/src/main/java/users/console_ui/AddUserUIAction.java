package users.console_ui;

import users.database.Database;
import users.User;
import users.services.AddUserService;

import java.util.Scanner;

public class AddUserUIAction implements UIAction {

    private AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        addUserService.execute(username, password);
    }
}
