package workshop.console_ui;

import workshop.core.requests.RemoveCarRequest;
import workshop.core.responses.RemoveCarResponse;
import workshop.core.services.RemoveCarService;

import java.util.Scanner;

public class RemoveCarUIAction implements UIAction {
    private RemoveCarService removeCarService;

    public RemoveCarUIAction(RemoveCarService removeCarService) {
        this.removeCarService = removeCarService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car id to remove: ");
        Long carId = Long.parseLong(scanner.nextLine());
        RemoveCarRequest request = new RemoveCarRequest(carId);
        RemoveCarResponse response = removeCarService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isCarRemoved()) {
                System.out.println("Your car was removed from list.");
            } else {
                System.out.println("Your car not removed from list.");
            }
        }

    }
}
