package java2.fitness_app.users.users.console_ui;

import java2.fitness_app.users.users.core.requests.GetAllUsersRequest;
import java2.fitness_app.users.users.core.response.GetAllUsersResponse;
import java2.fitness_app.users.users.core.services.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {

    private GetAllUsersService getUsersService;

    public GetAllUsersUIAction(GetAllUsersService getUsersService) {
        this.getUsersService = getUsersService;
    }

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = getUsersService.execute(request);
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }

}
