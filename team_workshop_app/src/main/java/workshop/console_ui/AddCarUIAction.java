package workshop.console_ui;

import workshop.core.requests.AddCarRequest;
import workshop.core.responses.AddCarResponse;
import workshop.core.services.AddCarService;

import java.util.Scanner;

public class AddCarUIAction implements UIAction {

    private AddCarService addCarService;

    public AddCarUIAction(AddCarService addCarService) {
        this.addCarService = addCarService;
    }

    @Override
    public void execute() {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter car brand: ");
            String brand = scanner.nextLine();
            System.out.println("Enter car model: ");
            String model = scanner.nextLine();
            System.out.println("Enter car number: ");
            String number = scanner.nextLine();
            System.out.println("Enter car color code: ");
            String colorCode = scanner.nextLine();
            AddCarRequest request = new AddCarRequest(brand,model,number,colorCode);
            AddCarResponse response = addCarService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Your Car was added to list.");
        }


    }
}
