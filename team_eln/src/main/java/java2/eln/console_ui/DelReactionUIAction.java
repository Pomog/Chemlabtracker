package java2.eln.console_ui;

import java2.eln.core.requests.DelReactionRequest;
import java2.eln.core.responses.DelReactionResponse;
import java2.eln.core.services.DelReactionService;

import java.util.Scanner;

public class DelReactionUIAction implements UIAction{
    private final DelReactionService delReactionService;

    public DelReactionUIAction(DelReactionService delReactionService) {
        this.delReactionService = delReactionService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code to delete: ");
        String  reactionCode = scanner.nextLine();

        DelReactionRequest delReactionRequest = new DelReactionRequest(reactionCode);
        DelReactionResponse delReactionResponse = delReactionService.execute(delReactionRequest);

        if (delReactionResponse.hasErrors()) {
            delReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.printf("Reaction %s has been deleted -> %s", reactionCode, delReactionResponse.getDelResult());
        }

    }
}
