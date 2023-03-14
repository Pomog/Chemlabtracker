package console_ui;

import eln.database.Database;
import eln.database.User;

import java.util.Scanner;

public class AddUserUIAction implements UIAction {

    private Database database;

    public AddUserUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        database.registerNewUser(user);
        System.out.println("eln.database.User registered with user ID " + user.getId());
    }
}
