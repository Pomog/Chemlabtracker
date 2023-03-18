package lv.javaguru.java2.servify;

import lv.javaguru.java2.servify.console_ui.*;
import lv.javaguru.java2.servify.database.Database;
import lv.javaguru.java2.servify.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.servify.service.AddDetailService;
import lv.javaguru.java2.servify.service.GetAllDetailsService;
import lv.javaguru.java2.servify.service.GetTotalPriceService;
import lv.javaguru.java2.servify.service.RemoveDetailService;

import java.util.Scanner;

public class SwitchMenuTEST {

    private static Database database = new InMemoryDatabaseImpl();
    private static AddDetailService addDetailService = new AddDetailService(database);
    private static RemoveDetailService removeDetailService = new RemoveDetailService(database);
    private static GetAllDetailsService getAllDetailsService = new GetAllDetailsService(database);
    private static GetTotalPriceService getTotalPriceService = new GetTotalPriceService(database);
    private static UIAction addDetailUIAction = new AddDetailUIAction(addDetailService);
    private static UIAction removeDetailUIAction = new RemoveDetailUIAction(removeDetailService, getAllDetailsService);
    private static UIAction getAllDetailsUIAction = new GetAllDetailsUIAction(getAllDetailsService);
    private static UIAction getTotalPriceUIAction = new GetTotalPriceUIAction(getTotalPriceService);
    private static UIAction exitUIAction = new ExitUIAction();

    public void start() {
        while(true) {
            printProgramMenu();

            System.out.println("Enter menu item number to execute:");
            int userChoice = userChoiceFromMenu();
            executeSelectedMenuItem(userChoice);
            System.out.println();
        }
    }
    public static void printProgramMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add detail to list");
        System.out.println("2. Delete detail from list");
        System.out.println("3. Show all detail in the list");
        System.out.println("4. See total price");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt();
    }

        private static void executeSelectedMenuItem ( int userChoice){
            switch (userChoice) {
                case 1 -> addDetailUIAction.execute();
                case 2 -> removeDetailUIAction.execute();
                case 3 -> getAllDetailsUIAction.execute();
                case 4 -> getTotalPriceUIAction.execute();
                case 5 -> exitUIAction.execute();
            }
        }
}








