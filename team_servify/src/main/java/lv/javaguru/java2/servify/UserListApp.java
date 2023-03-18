package lv.javaguru.java2.servify;

import lv.javaguru.java2.servify.console_ui.AddUserUIAction;
import lv.javaguru.java2.servify.console_ui.ExitUIAction;
import lv.javaguru.java2.servify.console_ui.GetAllUsersUIAction;
import lv.javaguru.java2.servify.console_ui.SetUserNotActiveUIAction;
import lv.javaguru.java2.servify.database.UsersDatabase;
import lv.javaguru.java2.servify.database.UsersInMemoryDatabaseImpl;
import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.service.AddUserService;
import lv.javaguru.java2.servify.service.GetAllUsersService;
import lv.javaguru.java2.servify.service.SetUserNotActiveService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserListApp {
    private static UsersDatabase userDB = new UsersInMemoryDatabaseImpl();
    private static AddUserService addUserService = new AddUserService(userDB);
    private static AddUserUIAction addUserUIAction = new AddUserUIAction(addUserService);
    private static GetAllUsersService getAllUsersService = new GetAllUsersService(userDB);
    private static GetAllUsersUIAction getAllUsersUIAction = new GetAllUsersUIAction(getAllUsersService);
    private static SetUserNotActiveService setUserNotActiveService = new SetUserNotActiveService(userDB);
    private static SetUserNotActiveUIAction setUserNotActiveUIAction = new SetUserNotActiveUIAction(setUserNotActiveService);
    private static ExitUIAction exitUIAction = new ExitUIAction();
    private static SwitchMenuTEST switchMenuTEST = new SwitchMenuTEST();

    public static void start() {
        List<UserEntity> usersDB = new ArrayList<>();

        while (true) {
            printAdminMenu();
            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1 -> addUserUIAction.execute();
                case 2 -> setUserNotActiveUIAction.execute();
                case 3 -> getAllUsersUIAction.execute();
                case 4 -> switchMenuTEST.start();
                case 5 -> exitUIAction.execute();

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
        System.out.println("1. Registration Add user to list");
        System.out.println("2. Login");

        System.out.println("1. Add user to list");
        System.out.println("2. Delete user from list");
        System.out.println("3. Show all users in the list");
        System.out.println("4. DetailsApp");
        System.out.println("5. Exit");

        System.out.println("");
    }
}
