package java2.eln.console_ui;

import java2.eln.services.GetAllReactionsService;

public class GetAllReactionUIAction implements UIAction{
    private GetAllReactionsService getAllReactionsService;

    public GetAllReactionUIAction(GetAllReactionsService getAllReactionsService) {
        this.getAllReactionsService = getAllReactionsService;
    }

    @Override
    public void execute() {
        System.out.println("Reaction IMDataBase :");
        getAllReactionsService.execute().forEach(System.out::println);
        System.out.println("Reaction log end.");

    }
}
