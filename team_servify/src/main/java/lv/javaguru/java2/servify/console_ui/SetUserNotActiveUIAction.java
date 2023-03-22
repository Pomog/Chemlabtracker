package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.service.SetUserNotActiveService;

import java.util.Scanner;

public class SetUserNotActiveUIAction implements UIAction {
    private SetUserNotActiveService setUserNotActiveService;

    public SetUserNotActiveUIAction(SetUserNotActiveService setUserNotActiveService) {
        this.setUserNotActiveService = setUserNotActiveService;
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter User id to remove: ");
        Long userId = Long.parseLong(input.nextLine());
        setUserNotActiveService.act(userId);
        System.out.println("Your user deactivated.");
    }
}
