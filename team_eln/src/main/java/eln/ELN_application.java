package eln;

import consoleUI.AddReactionUIAction;
import consoleUI.ExitUIAction;
import database.DatabaseIM;
import database.InMemoryDatabaseImplIM;
import services.AddReactionService;

import java.util.Scanner;

public class ELN_application {
    private static DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();
    private static AddReactionService addReactionService = new AddReactionService(inMemoryDataBase);
    private static AddReactionUIAction addReactionUIAction = new AddReactionUIAction(addReactionService);
    private static ExitUIAction exitUIAction = new ExitUIAction();


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
        System.out.println("1. Add Reaction to list");
        System.out.println("2. Delete Reaction from list");
        System.out.println("3. Show all Reactions in the list");
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
            case 1: {
                addReactionUIAction.execute();
                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {
                exitUIAction.execute();
                break;
            }
        }
    }

}

