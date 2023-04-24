package java2.eln.console_ui;

import java2.eln.core.requests.DeleteReactionRequest;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.services.DelReactionService;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DelReactionUIAction implements UIAction{

    @DIDependency
    DelReactionService delReactionService;

//    public DelReactionUIAction(DelReactionService delReactionService) {
//        this.delReactionService = delReactionService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code to delete: ");
        String  reactionCode = scanner.nextLine();

        DeleteReactionRequest deleteReactionRequest = new DeleteReactionRequest(reactionCode);
        DeleteReactionResponse deleteReactionResponse = delReactionService.execute(deleteReactionRequest);

        if (deleteReactionResponse.hasErrors()) {
            deleteReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.printf("Reaction %s has been deleted -> %s", reactionCode, deleteReactionResponse.getDelResult());
        }

    }
}
