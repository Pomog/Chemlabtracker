package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.service.AddUserService;

import java.util.Scanner;

public class AddUserUIAction implements UIAction {
    private AddUserService addUserService;

    public AddUserUIAction(AddUserService addUserService) {
        this.addUserService = addUserService;
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your First Name");
        String firstName = input.nextLine();
        System.out.println("Enter your Second Name");
        String secondName = input.nextLine();
        System.out.println("Enter your NickName");
        String nickName = input.nextLine();
        System.out.println("Enter your e-mail");
        String email = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.nextLine();
        UserEntity user = new UserEntity(firstName, secondName, nickName, email, phoneNumber);
        addUserService.act(user);
    }
}
