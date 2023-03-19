package lv.javaguru.java2.bankapp.console_ui;

import lv.javaguru.java2.bankapp.services.DeleteUsersService;
import java.util.Scanner;

public class DeleteUsersUIAction implements UIAction {
    private DeleteUsersService deleteUsersService;

    public DeleteUsersUIAction(DeleteUsersService deleteUsersService) {

        this.deleteUsersService=deleteUsersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String newSurname = scanner.nextLine();
        System.out.println("Enter you gender : ");
        String newGender = scanner.nextLine();
        System.out.println("Enter you age: ");
        int newAge = scanner.nextInt();
        deleteUsersService.deleteUsers(newName, newSurname, newGender, newAge);
        System.out.println("Your Username was removed from list.");

    }
}
