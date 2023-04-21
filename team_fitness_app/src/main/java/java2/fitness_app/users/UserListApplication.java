package java2.fitness_app.users;

import java2.fitness_app.users.dependency_injection.ApplicationContext;
import java2.fitness_app.users.dependency_injection.DIApplicationContextBuilder;
import java2.fitness_app.users.console_ui.*;

import java.util.Scanner;

public class UserListApplication {

    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("java2.fitness_app");


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
            case 1 -> {
                AddUserUIAction uiAction = applicationContext.getBean(AddUserUIAction.class);
                uiAction.execute();
            }
            case 2 -> {
                LoginUserUIAction uiAction = applicationContext.getBean(LoginUserUIAction.class);
                uiAction.execute();
            }
            case 3 -> {
                RemoveUserUIAction uiAction = applicationContext.getBean(RemoveUserUIAction.class);
                uiAction.execute();
            }
            case 4 -> {
                GetAllUsersUIAction uiAction = applicationContext.getBean(GetAllUsersUIAction.class);
                uiAction.execute();
            }
            case 5 -> {
                ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                uiAction.execute();
            }
        }
    }
}