package java2.eln;

import java2.eln.console_ui.*;
import java2.eln.core.database.DatabaseIM;
import java2.eln.core.database.InMemoryDatabaseImplIM;
import java2.eln.core.services.AddReactionService;
import java2.eln.core.services.DelReactionService;
import java2.eln.core.services.FindReactionsByMainProductService;
import java2.eln.core.services.GetAllReactionsService;

import java.util.Scanner;

public class ELN_application {
    private static DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();
    private static AddReactionService addReactionService = new AddReactionService(inMemoryDataBase);
    private static UIAction addReactionUIAction = new AddReactionUIAction(addReactionService);
    private static GetAllReactionsService getAllReactionsService = new GetAllReactionsService(inMemoryDataBase);
    private static UIAction getAllReactionUIAction = new GetAllReactionUIAction(getAllReactionsService);
    private static DelReactionService delReactionService = new DelReactionService(inMemoryDataBase);
    private static UIAction delReactionUIACtion = new DelReactionUIAction(delReactionService);
    private static FindReactionsByMainProductService findReactionByMainProductService =
            new FindReactionsByMainProductService(inMemoryDataBase);
    private static FindReactionByMainProductUIAction findReactionByMainProductUIAction =
            new FindReactionByMainProductUIAction(findReactionByMainProductService);
    private static UIAction exitFormApplication = new ExitUIAction();


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
        System.out.println("4. Find reactions by main product");
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
                addReactionUIAction.execute();
                break;
            }
            case 2 -> {
                delReactionUIACtion.execute();
                break;
            }
            case 3 -> {
                getAllReactionUIAction.execute();
                break;
            }
            case 4 -> {
                findReactionByMainProductUIAction.execute();
                break;
            }
            case 5 -> {
                exitFormApplication.execute();
                break;
            }
        }
    }

}

