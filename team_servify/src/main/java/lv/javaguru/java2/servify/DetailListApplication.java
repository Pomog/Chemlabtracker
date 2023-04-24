package lv.javaguru.java2.servify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lv.javaguru.java2.servify.config.DetailListConfiguration;
import lv.javaguru.java2.servify.console_ui.*;
import lv.javaguru.java2.servify.console_ui.detail.AddDetailUIAction;
import lv.javaguru.java2.servify.console_ui.detail.GetAllDetailsUIAction;
import lv.javaguru.java2.servify.console_ui.detail.GetTotalPriceUIAction;
import lv.javaguru.java2.servify.console_ui.detail.RemoveDetailUIAction;
import lv.javaguru.java2.servify.console_ui.user.AddUserUIAction;

import java.util.Scanner;

class DetailListApplication {

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(DetailListConfiguration.class);

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        showWelcomeMessage();
        while (true) {
            printProgramMenu();
            int userChoice = userChoiceFromMenu();
            if (userChoice < 1 || userChoice > 8) {
                showErrorMessage();
            } else {
                executeSelectedMenuItem(userChoice);
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add detail to list");
        System.out.println("2. Delete detail from list");
        System.out.println("3. Show all detail in the list");
        System.out.println("4. See total price");
        System.out.println("5. Registration");
        System.out.println("6. Login");
        System.out.println("7. Exit");
        System.out.println("8. TEST User menu");
        System.out.println();
        System.out.println("Enter menu item number to execute:");
        System.out.println();
    }

    private static void showErrorMessage() {
        System.out.println("Wrong input, try again, please use only 1 .. 8 for main menu selection.");
        System.out.println();
    }

    private static void showWelcomeMessage() {
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println("| Welcome to the SERVIFY APP - calculate price for your paint job |");
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println();
    }


    private static void executeSelectedMenuItem(int userChoice) {
        switch (userChoice) {
            case 1 -> {
                AddDetailUIAction uiAction = applicationContext.getBean(AddDetailUIAction.class);
                uiAction.execute();
            }
            case 2 -> {
                RemoveDetailUIAction uiAction = applicationContext.getBean(RemoveDetailUIAction.class);
                uiAction.execute();
            }
            case 3 -> {
                GetAllDetailsUIAction uiAction = applicationContext.getBean(GetAllDetailsUIAction.class);
                uiAction.execute();
            }
            case 4 -> {
                GetTotalPriceUIAction uiAction = applicationContext.getBean(GetTotalPriceUIAction.class);
                uiAction.execute();
            }
            case 5 -> {
                AddUserUIAction uiAction = applicationContext.getBean(AddUserUIAction.class);
                uiAction.execute();
            }
            case 6 -> System.out.println("Login TODO");
            case 7 -> {
                ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                uiAction.execute();
            }
            case 8 -> {
                UserMenuTest test = applicationContext.getBean(UserMenuTest.class);
                test.execute();
            }
        }

    }

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt();
    }

}

