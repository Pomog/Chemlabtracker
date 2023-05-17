package java2.eln.console_ui;

import java2.eln.core.requests.GetAllReactionsRequest;
import java2.eln.core.responses.GetAllReactionsResponse;
import java2.eln.core.services.GetAllReactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllReactionUIAction implements UIAction{

    @Autowired
    GetAllReactionsService getAllReactionsService;

//    public GetAllReactionUIAction(GetAllReactionsService getAllReactionsService) {
//        this.getAllReactionsService = getAllReactionsService;
//    }

    @Override
    public void execute() {
        System.out.println("Reaction IMDataBase :");

        GetAllReactionsRequest getAllReactionsRequest =
                new GetAllReactionsRequest();
        GetAllReactionsResponse getAllReactionsResponse =
                getAllReactionsService.execute(getAllReactionsRequest);

        System.out.println("Search results");
        getAllReactionsResponse.getReactionsList().forEach(System.out::println);
        System.out.println("Reactions log end.");

    }
}
