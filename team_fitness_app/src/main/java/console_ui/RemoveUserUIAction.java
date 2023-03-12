package console_ui;

import database.Database;

import java.util.Scanner;

public class RemoveUserUIAction implements UIAction{

    private Database database;

    public RemoveUserUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (database.deleteUser(id, password))
            System.out.println("database.User was removed from database.");
        else
            System.out.println("Id or Password is Incorrect!");
    }
}
