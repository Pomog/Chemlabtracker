package users.console_ui;

import users.core.services.GetUsersService;

public class GetUsersUIAction implements UIAction {

    private GetUsersService getUsersService;

    public GetUsersUIAction(GetUsersService getUsersService) {
        this.getUsersService = getUsersService;
    }

    @Override
    public void execute() {
        System.out.println("User list: ");
        getUsersService.execute().forEach(System.out::println);
        System.out.println("User list end.");
    }
}
