package lv.javaguru.java2.servify;

import lv.javaguru.java2.servify.console_ui.user.AddUserUIAction;
import lv.javaguru.java2.servify.console_ui.ExitUIAction;
import lv.javaguru.java2.servify.console_ui.user.GetAllUsersUIAction;
import lv.javaguru.java2.servify.console_ui.user.SetUserNotActiveUIAction;
import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.services.user.AddUserService;
import lv.javaguru.java2.servify.core.services.user.AddUserValidator;
import lv.javaguru.java2.servify.core.services.user.GetAllUsersService;
import lv.javaguru.java2.servify.core.services.user.SetUserNotActiveService;

import java.util.Scanner;

public class UserMenuTest {
    public void start(UsersDatabase userDB) {
        AddUserValidator addUserValidator = new AddUserValidator();
        AddUserService addUserService = new AddUserService(userDB, addUserValidator);
        AddUserUIAction addUserUIAction = new AddUserUIAction(addUserService);
        GetAllUsersService getAllUsersService = new GetAllUsersService(userDB);
        GetAllUsersUIAction getAllUsersUIAction = new GetAllUsersUIAction(getAllUsersService);
        SetUserNotActiveService setUserNotActiveService = new SetUserNotActiveService(userDB);
        SetUserNotActiveUIAction setUserNotActiveUIAction = new SetUserNotActiveUIAction(setUserNotActiveService);
        ExitUIAction exitUIAction = new ExitUIAction();

        while (true) {
            printAdminMenu();
            int userChoice = getUserChoice();
            switch (userChoice) {
                case 1 -> addUserUIAction.execute();
                case 2 -> setUserNotActiveUIAction.execute();
                case 3 -> getAllUsersUIAction.execute();
                case 4 -> exitUIAction.execute();
            }
        }
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printAdminMenu() {
        System.out.println("User menu:");
        System.out.println("1. Add user to list");
        System.out.println("2. Delete user from list");
        System.out.println("3. Show all users in the list");
        System.out.println("4. Exit");
        System.out.println();
    }
}
