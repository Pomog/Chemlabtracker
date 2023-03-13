package consoleUI;

import services.GetAllReactionsService;

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
