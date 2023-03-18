package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.service.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {
    private GetAllUsersService getAllUsersService;

    public GetAllUsersUIAction(GetAllUsersService getAllUsersService) {
        this.getAllUsersService = getAllUsersService;
    }

    @Override
    public void execute() {
        System.out.println("Book list: ");
        getAllUsersService.act().forEach(System.out::println);
        System.out.println("Book list end.");
    }
}
