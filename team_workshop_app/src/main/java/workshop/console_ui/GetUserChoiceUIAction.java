package workshop.console_ui;

import java.util.Scanner;

public class GetUserChoiceUIAction {
    public int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;
    }

}
