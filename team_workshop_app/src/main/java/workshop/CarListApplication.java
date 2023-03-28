package workshop;

import workshop.console_ui.AddCarUIAction;
import workshop.console_ui.ExitUIAction;
import workshop.console_ui.GetAllCarUIAction;
import workshop.console_ui.RemoveCarUIAction;
import workshop.core.database.Database;
import workshop.core.database.InMemoryDatabaseImpl;
import workshop.core.services.AddCarService;
import workshop.core.services.GetAllCarService;
import workshop.core.services.RemoveCarService;

import java.util.Scanner;

public class CarListApplication {
    public static void main(String[] args) {
        Database database = new InMemoryDatabaseImpl();


        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            executeSelectedMenuItem(database, userChoice);
        }
    }

    private static void executeSelectedMenuItem(Database database, int userChoice) {
        switch (userChoice) {
            case 1: {
                AddCarValidator validator = new AddCarValidator();
                AddCarService addCarService = new AddCarService(database,validator);
                AddCarUIAction addCarToList = new AddCarUIAction(addCarService);
                addCarToList.execute();
                break;
            }
            case 2: {
                RemoveCarService removeCarService = new RemoveCarService(database);
                RemoveCarUIAction removeCarFromList = new RemoveCarUIAction(removeCarService);
                removeCarFromList.execute();
                break;
            }
            case 3: {
                GetAllCarService getAllCarService = new GetAllCarService(database);
                GetAllCarUIAction showCarList = new GetAllCarUIAction(getAllCarService);
                showCarList.execute();
                break;
            }
            case 4: {
                ExitUIAction exit = new ExitUIAction();
                exit.execute();
            }
        }
    }








    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;
    }

    private static void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add car to list");
        System.out.println("2. Delete car from list");
        System.out.println("3. Show all cars in the list");
        System.out.println("4. Exit");
        System.out.println("");

    }


}
