import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DetailListApplication {

    private final List<Detail> details = new ArrayList<>();
    private final DetailChoice detailChoice = new DetailChoice();
    private static final DetailListApplication APPLICATION = new DetailListApplication();

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        while (true) {
            APPLICATION.printMenu();

            System.out.println("Enter menu item number to execute:");

            int userChoice = APPLICATION.userChoiceFromMenu();

            if (userChoice < 1 || userChoice > 4) {
                System.out.println("Wrong input, try again, please use only 1 .. 4 for main menu selection.");
                continue;
            } else {
                APPLICATION.menuSwitchLogic(userChoice);
            }
            System.out.println();
        }

    }

    private void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add detail to list");
        System.out.println("2. Delete detail from list");
        System.out.println("3. Show all detail in the list");
        System.out.println("4. Exit");
        System.out.println();
    }

    private int userChoiceFromMenu() {
        String userInput = new Scanner(System.in).nextLine();
        if (!userInput.matches("[1-7]")) {
            System.out.println("Wrong input, please enter only 1 .. 4 for main menu, 1 .. 7 for detail choice, 1 .. 2 for position and side menus!");
        }
        return Integer.parseInt(userInput);
    }

    private void menuSwitchLogic(int userChoice) {
        switch (userChoice) {
            case 1 -> {
                APPLICATION.addDetail();
                System.out.println("Your detail was added to list.");
            }
            case 2 -> {
                System.out.println("You chose to remove details from the list. Please enter detail to remove:");
                APPLICATION.printDetailList();

                if (details.isEmpty()) {
                    System.out.println("You don't have any detail to remove!");
                } else {
                    if (APPLICATION.removeDetail(userChoiceFromMenu())) {
                        System.out.println("Your detail was removed from the list.");
                    } else {
                        System.out.println("Wrong input, try again, please");
                    }
                }
            }
            case 3 -> {
                System.out.println("Detail list: ");
                APPLICATION.printDetailList();
                System.out.println("Detail list end.");
            }
            case 4 -> {
                System.out.println("Good bye!");
                System.exit(0);
            }
        }
    }

    private void addDetail() {
        APPLICATION.details.add(APPLICATION.detailChoice.detailChoice());
    }

    private void printDetailList() {
        for (int i = 0; i < details.size(); i++) {
            System.out.println((i + 1) + ". " + details.get(i).toString());
        }
    }

    private boolean removeDetail(int removingDetailNumber) {
        if (removingDetailNumber > 0 && removingDetailNumber <= details.size() + 1) {
            details.remove(removingDetailNumber - 1);
            return true;
        }
        return false;
    }

}

