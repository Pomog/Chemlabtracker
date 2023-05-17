package java2.eln.console_ui;

import java2.eln.core.requests.AddReactionRequest;
import java2.eln.core.responses.AddReactionResponse;
import java2.eln.core.services.AddReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddReactionUIAction implements UIAction{

    @Autowired
    AddReactionService addReactionService;

//    public AddReactionUIAction(AddReactionService addReactionService) {
//        this.addReactionService = addReactionService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code: ");
        String reactionCode = scanner.nextLine();
        System.out.println("Enter Reaction Name: ");
        String reactionName = scanner.nextLine();
        System.out.println("Enter file path with Reaction Data: " + "example: team_eln/data/demoReaction1.txt");
        String dataFilePath = scanner.nextLine();

        AddReactionRequest addReactionRequest = new AddReactionRequest(reactionCode, reactionName, dataFilePath);
        AddReactionResponse addReactionResponse = addReactionService.execute(addReactionRequest);

        if (addReactionResponse.hasErrors()) {
            addReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.printf("Reaction %s has been successfully added.", addReactionResponse.getReactionData().getCode());
        }
    }
}
