package java2.eln;

import java2.eln.console_ui.*;
import java2.eln.core.database.DatabaseIM;
import java2.eln.core.database.InMemoryDatabaseImplIM;
import java2.eln.core.services.*;
import java2.eln.core.services.validators.AddReactionValidator;
import java2.eln.core.services.validators.DelReactionValidator;
import java2.eln.core.services.validators.FindReactionValidator;
import java2.eln.dependency_injection.ApplicationContext;
import java2.eln.dependency_injection.DIApplicationContextBuilder;

import java.util.Scanner;

public class ELN_application {
//    private static DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();
//    private static AddReactionValidator addReactionValidator = new AddReactionValidator();
//    private static AddReactionService addReactionService =
//            new AddReactionService(inMemoryDataBase, addReactionValidator);
//    private static UIAction addReactionUIAction = new AddReactionUIAction(addReactionService);
//    private static GetAllReactionsService getAllReactionsService = new GetAllReactionsService(inMemoryDataBase);
//    private static UIAction getAllReactionUIAction = new GetAllReactionUIAction(getAllReactionsService);
//    private static DelReactionValidator delReactionValidator =
//            new DelReactionValidator(inMemoryDataBase);
//    private static DelReactionService delReactionService = new DelReactionService(inMemoryDataBase, delReactionValidator);
//    private static UIAction delReactionUIAction = new DelReactionUIAction(delReactionService);
//    private static FindReactionsByMainProductService findReactionByMainProductService =
//            new FindReactionsByMainProductService(inMemoryDataBase);
//    private static FindReactionByMainProductUIAction findReactionByMainProductUIAction =
//            new FindReactionByMainProductUIAction(findReactionByMainProductService);
//    private static FindReactionValidator findReactionValidator =
//            new FindReactionValidator();
//    private static FindReactionService findReactionService =
//            new FindReactionService(inMemoryDataBase, findReactionValidator);
//    private static FindReactionUIAction findReactionUIAction =
//            new FindReactionUIAction(findReactionService);
//    private static ExitUIService exitUIService =
//            new ExitUIService();
//    private static UIAction exitFormApplication = new ExitUIAction(exitUIService);

    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("java2.eln");


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
        System.out.println("5. Find reactions");
        System.out.println("6. Exit");
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
                AddReactionUIAction addReactionUIAction = applicationContext.getBean(AddReactionUIAction.class);
                addReactionUIAction.execute();
                break;
            }
            case 2 -> {
                DelReactionUIAction delReactionUIAction = applicationContext.getBean(DelReactionUIAction.class);
                delReactionUIAction.execute();
                break;
            }
            case 3 -> {
                GetAllReactionUIAction getAllReactionUIAction = applicationContext.getBean(GetAllReactionUIAction.class);
                getAllReactionUIAction.execute();
                break;
            }
            case 4 -> {
                FindReactionByMainProductUIAction findReactionByMainProductUIAction = applicationContext.getBean(FindReactionByMainProductUIAction.class);
                findReactionByMainProductUIAction.execute();
                break;
            }
            case 5 -> {
                FindReactionUIAction findReactionUIAction = applicationContext.getBean(FindReactionUIAction.class);
                findReactionUIAction.execute();
                break;
            }
            case 6 -> {
                ExitUIAction exitUIAction = applicationContext.getBean(ExitUIAction.class);
                exitUIAction.execute();
                break;
            }
            default -> {break;}
        }
    }

}

