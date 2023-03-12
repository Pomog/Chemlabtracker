package eln.database;

import console_ui.*;

import java.util.Scanner;

public class UserListApplication {


    private static Database database = new InMemoryDatabaseImpl();
    private static UIAction addUserUIAction = new AddUserUIAction(database);
    private static UIAction removeUserUIAction = new RemoveUserUIAction(database);
    private static UIAction validateUserUIAction = new ValidateUserUIAction(database);
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
        System.out.println("3. Delete user from eln.database.");
        System.out.println("4. Exit");
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
            case 2 -> validateUserUIAction.execute();
            case 3 -> removeUserUIAction.execute();
            case 4 -> exitUIAction.execute();
        }
    }
}