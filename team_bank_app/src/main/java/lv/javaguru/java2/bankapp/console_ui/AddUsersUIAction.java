package lv.javaguru.java2.bankapp.console_ui;

import lv.javaguru.java2.bankapp.services.AddUsersService;

import java.util.Scanner;

public class AddUsersUIAction implements UIAction{
    private AddUsersService addUsersService;

    public AddUsersUIAction(AddUsersService addUsersService) {
        this.addUsersService=addUsersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter you surname: ");
        String newSurname = scanner.nextLine();
        System.out.println("Enter you gender : ");
        String newGender = scanner.nextLine();
        System.out.println("Enter you age: ");
        int newAge = scanner.nextInt();
        addUsersService.addUsers(newName, newSurname, newGender, newAge);
        System.out.println("Your Username was added to list.");
    }

    }

