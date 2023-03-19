package lv.javaguru.java2.bankapp.console_ui;

import lv.javaguru.java2.bankapp.services.PrintUsersService;
import lv.javaguru.java2.bankapp.domain.User;

public class PrintUsersAction implements UIAction {
    private PrintUsersService printUsersService;

    public PrintUsersAction(PrintUsersService printUsersService) {
        this.printUsersService = printUsersService;
    }

    @Override
    public void execute() {
        System.out.println("Users list: ");
        for (User user : printUsersService.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println("Users list end.");

    }
}
