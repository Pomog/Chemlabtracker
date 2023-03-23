package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.core.services.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {
    private GetAllUsersService getAllUsersService;

    public GetAllUsersUIAction(GetAllUsersService getAllUsersService) {
        this.getAllUsersService = getAllUsersService;
    }

    @Override
    public void execute() {
        System.out.println("User list: ");
        getAllUsersService.act().forEach(System.out::println);
        System.out.println("User list end.");
        System.out.println();
    }
}
