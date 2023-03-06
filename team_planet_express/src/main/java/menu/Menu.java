package menu;

import java.util.Scanner;

public class Menu {
    MenuHandler handler;

    public Menu(MenuHandler handler) {
        this.handler = handler;
    }

    public void run() {
        while (handler.isWorking) {
            System.out.println("Program menu:");
            for (String str : handler.getListMethod()) {
                System.out.println(str);
            }
            System.out.println();
            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            try {
                int userChoice = Integer.parseInt(scanner.nextLine());
                this.handler.handleChoice(userChoice);
            } catch (NumberFormatException e) {
                System.out.println("Please type number!");
                System.out.println();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
