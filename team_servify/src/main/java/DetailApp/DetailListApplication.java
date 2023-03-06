package DetailApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DetailListApplication {

    private final List<Detail> details = new ArrayList<>();
    private final DetailChoice detailChoice = new DetailChoice();

    public static void main(String[] args) {

        DetailListApplication app = new DetailListApplication();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add detail to list");
            System.out.println("2. Delete detail from list");
            System.out.println("3. Show all detail in the list");
            System.out.println("4. Exit");

            System.out.println();

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> {
                    app.details.add(app.detailChoice.detailChoice());
                    System.out.println("Your detail was added to list.");
                }
                case 2 -> {
                    if (app.details.isEmpty()) {
                        System.out.println("You don't have any detail to remove!");
                    } else {
                        Detail detailToRemove = app.detailChoice.detailChoice();
                        if (app.details.contains(detailToRemove)) {
                            app.details.remove(detailToRemove);
                            System.out.println("Your detail " + detailToRemove + " was removed from list.");
                        }
                        else System.out.println("You don't have " + detailToRemove + " in your list!");
                    }
                }
                case 3 -> {
                    System.out.println("Detail list: ");
                    for (Detail detail : app.details) {
                        System.out.println(detail);
                    }
                    System.out.println("Detail list end.");
                }
                case 4 -> {
                    System.out.println("Good bye!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Choose the variant from the menu, please.");
                    continue;
                }
            }
            System.out.println();
        }

    }

}

