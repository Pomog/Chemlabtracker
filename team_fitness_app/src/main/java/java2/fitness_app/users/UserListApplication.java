package java2.fitness_app.users;


import java2.fitness_app.users.console_ui.*;
import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.core.services.*;

import java.util.Scanner;

public class UserListApplication {

    private static Database database = new InMemoryDatabaseImpl();
    private static AddUserRequestValidator addUserRequestValidator = new AddUserRequestValidator();
    private static LoginUserValidator loginUserValidator = new LoginUserValidator();
    private static AddUserService addUserService = new AddUserService(database, addUserRequestValidator);
    private static LoginUserService loginUserService = new LoginUserService(database, loginUserValidator);
    private static RemoveUserValidator removeUserValidator = new RemoveUserValidator();
    private static RemoveUserService removeUserService = new RemoveUserService(database, removeUserValidator);
    private static GetAllUsersService getUsersService = new GetAllUsersService(database);


    private static UIAction addUserUIAction = new AddUserUIAction(addUserService);
    private static UIAction loginUserUIAction = new LoginUserUIAction(loginUserService);
    private static UIAction removeUserUIAction = new RemoveUserUIAction(removeUserService);
    private static UIAction getAllUsersUIAction = new GetAllUsersUIAction(getUsersService);


    private static UIAction exitUIAction = new ExitUIAction();


    public static void main(String[] args) {

        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Register new user.");
        System.out.println("2. Login.");
        System.out.println("3. Delete user from database.");
        System.out.println("4. Show all users in the list");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int selectedMenu) {

        switch (selectedMenu) {
            case 1 -> addUserUIAction.execute();
            case 2 -> loginUserUIAction.execute();
            case 3 -> removeUserUIAction.execute();
            case 4 -> getAllUsersUIAction.execute();
            case 5 -> exitUIAction.execute();
        }
    }
}