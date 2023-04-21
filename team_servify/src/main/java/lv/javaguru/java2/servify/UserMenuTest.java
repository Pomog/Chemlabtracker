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
import lv.javaguru.java2.servify.dependency_injection.ApplicationContext;
import lv.javaguru.java2.servify.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class UserMenuTest {

    @DIDependency private UsersDatabase usersDatabase;

    public void execute() {

        ApplicationContext applicationContext =
                new DIApplicationContextBuilder().build("lv.javaguru.java2.servify");

        while (true) {
            printAdminMenu();
            int userChoice = getUserChoice();
            switch (userChoice) {
                case 1 -> {
                    AddUserUIAction uiAction = applicationContext.getBean(AddUserUIAction.class);
                    uiAction.execute();
                }
                case 2 -> {
                    SetUserNotActiveUIAction uiAction = applicationContext.getBean(SetUserNotActiveUIAction.class);
                    uiAction.execute();
                }
                case 3 -> {
                    GetAllUsersUIAction uiAction = applicationContext.getBean(GetAllUsersUIAction.class);
                    uiAction.execute();
                }
                case 4 -> {
                    ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                    uiAction.execute();
                }
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
