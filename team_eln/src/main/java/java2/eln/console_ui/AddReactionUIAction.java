package java2.eln.console_ui;

import java2.eln.services.AddReactionService;

import java.util.Scanner;

public class AddReactionUIAction implements UIAction{

    private AddReactionService addReactionService;

    public AddReactionUIAction(AddReactionService addReactionService) {
        this.addReactionService = addReactionService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code: ");
        String reactionCode = scanner.nextLine();
        System.out.println("Enter Reaction Name: ");
        String reactionName = scanner.nextLine();
        System.out.println("Enter file path with Reaction Data: " + "example: team_eln/data/demoReaction1.txt");
        String dataFilePath = scanner.nextLine();

        addReactionService.execute(reactionCode, reactionName, dataFilePath);

        System.out.println("Reaction was added");
    }
}
