import java.math.BigDecimal;
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

            if (userChoice < 1 || userChoice > 5) {
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
        System.out.println("4. See total price");
        System.out.println("5. Exit");
        System.out.println();
    }

    private int userChoiceFromMenu() {
        int result = 0; // looks crappy, maybe can do somehow else...
        String userInput = new Scanner(System.in).nextLine();
        if (!userInput.matches("[1-7]")) {
            System.out.println("Wrong input, please enter only 1 .. 4 for main menu, 1 .. 7 for detail choice, 1 .. 2 for position and side menus!");
        } else result = Integer.parseInt(userInput); // this was causing error - allow parse ONLY if it's number.
        return result;
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
                Detail bonnet = new Detail("Bonnet", "", "");
                Detail boot = new Detail("Boot", "", "");
                Detail frontBumper = new Detail("Bumper", "Front", "");
                Detail rearBumper = new Detail("Bumper", "Rear", "");
                Detail roof = new Detail("Roof", "", "");
                Detail frontLeftDoor = new Detail("Door", "Front", "Left");
                Detail frontRightDoor = new Detail("Door", "Front", "Right");
                Detail rearLeftDoor = new Detail("Door", "Rear", "Left");
                Detail rearRightDoor = new Detail("Door", "Rear", "Right");
                Detail frontLeftWing = new Detail("Wing", "Front", "Left");
                Detail frontRightWing = new Detail("Wing", "Front", "Right");
                Detail rearLeftWing = new Detail("Wing", "Rear", "Left");
                Detail rearRightWing = new Detail("Wing", "Rear", "Right");
                Detail leftWingMirror = new Detail("Wing mirror", "", "Left");
                Detail rightWingMirror = new Detail("Wing mirror", "", "Right");


                List<Detail> detailPricesList = new ArrayList<>();
                detailPricesList.add(bonnet);
                detailPricesList.add(boot);
                detailPricesList.add(roof);
                detailPricesList.add(frontBumper);
                detailPricesList.add(rearBumper);
                detailPricesList.add(frontLeftDoor);
                detailPricesList.add(frontRightDoor);
                detailPricesList.add(rearLeftDoor);
                detailPricesList.add(rearRightDoor);
                detailPricesList.add(frontLeftWing);
                detailPricesList.add(frontRightWing);
                detailPricesList.add(rearLeftWing);
                detailPricesList.add(rearRightWing);
                detailPricesList.add(leftWingMirror);
                detailPricesList.add(rightWingMirror);




                List<Detail> detailListWithPrices = new ArrayList<>();

                for(Detail detail: detailPricesList){
                    if (details.contains(detail)){
                        detailListWithPrices.add(detail);

                    }
                }
                //System.out.println(detailListWithPrices);

                bonnet.setPrice(BigDecimal.valueOf(200));
                boot.setPrice(BigDecimal.valueOf(180));
                frontBumper.setPrice(BigDecimal.valueOf(180));
                rearBumper.setPrice(BigDecimal.valueOf(150));
                roof.setPrice(BigDecimal.valueOf(250));
                frontLeftDoor.setPrice(BigDecimal.valueOf(180));
                frontRightDoor.setPrice(BigDecimal.valueOf(180));
                rearLeftDoor.setPrice(BigDecimal.valueOf(180));
                rearRightDoor.setPrice(BigDecimal.valueOf(180));
                frontLeftWing.setPrice(BigDecimal.valueOf(130));
                frontRightWing.setPrice(BigDecimal.valueOf(130));
                rearLeftWing.setPrice(BigDecimal.valueOf(160));
                rearRightWing.setPrice(BigDecimal.valueOf(160));
                leftWingMirror.setPrice(BigDecimal.valueOf(60));
                rightWingMirror.setPrice(BigDecimal.valueOf(60));

                BigDecimal totalPrice = BigDecimal.valueOf(0);
                for(Detail detail: detailListWithPrices){

                    totalPrice = totalPrice.add(detail.getPrice());

                }
                System.out.println("Total price: " + totalPrice);


            }
            case 5 -> {
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

