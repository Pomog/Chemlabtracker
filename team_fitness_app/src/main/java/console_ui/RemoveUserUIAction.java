package console_ui;

import eln.database.Database;

import java.util.Scanner;

public class RemoveUserUIAction implements UIAction{

    private Database database;

    public RemoveUserUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (database.deleteUser(id, password))
            System.out.println("eln.database.User was removed from eln.database.");
        else
            System.out.println("Id or Password is Incorrect!");
    }
}
