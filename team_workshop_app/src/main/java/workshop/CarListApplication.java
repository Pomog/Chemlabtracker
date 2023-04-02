package workshop;

import workshop.console_ui.*;
import workshop.core.services.AddCarValidator;
import workshop.core.database.Database;
import workshop.core.database.InMemoryDatabaseImpl;
import workshop.core.services.AddCarService;
import workshop.core.services.GetAllCarService;
import workshop.core.services.RemoveCarService;

public class CarListApplication {
    public static void main(String[] args) {
        Database database = new InMemoryDatabaseImpl();
        PrintMenuUIAction printMenuUIAction = new PrintMenuUIAction();
        GetUserChoiceUIAction getUserChoiceUIAction = new GetUserChoiceUIAction();


        while (true) {
            printMenuUIAction.execute();
            int userChoice = getUserChoiceUIAction.getUserChoice();
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
}
