package java2.eln.console_ui;

import java2.eln.core.requests.GetAllReactionsRequest;
import java2.eln.core.responses.GetAllReactionsResponse;
import java2.eln.core.services.GetAllReactionsService;

public class GetAllReactionUIAction implements UIAction{
    private GetAllReactionsService getAllReactionsService;

    public GetAllReactionUIAction(GetAllReactionsService getAllReactionsService) {
        this.getAllReactionsService = getAllReactionsService;
    }

    @Override
    public void execute() {
        System.out.println("Reaction IMDataBase :");

        GetAllReactionsRequest getAllReactionsRequest =
                new GetAllReactionsRequest();
        GetAllReactionsResponse getAllReactionsResponse =
                getAllReactionsService.execute(getAllReactionsRequest);

        getAllReactionsResponse.getReactionsList().forEach(System.out::println);
        System.out.println("Reaction log end.");

    }
}
