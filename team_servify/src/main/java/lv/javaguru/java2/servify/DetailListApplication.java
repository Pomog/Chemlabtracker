package lv.javaguru.java2.servify;

import lv.javaguru.java2.servify.console_ui.*;
import lv.javaguru.java2.servify.database.Database;
import lv.javaguru.java2.servify.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.servify.service.AddDetailService;
import lv.javaguru.java2.servify.service.GetAllDetailsService;
import lv.javaguru.java2.servify.service.GetTotalPriceService;
import lv.javaguru.java2.servify.service.RemoveDetailService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DetailListApplication {

//    private List<Detail> details = new ArrayList<>();
//    private static DetailListApplication APPLICATION = new DetailListApplication();

    private static Database database = new InMemoryDatabaseImpl();
    private static AddDetailService addDetailService = new AddDetailService(database);
    private static RemoveDetailService removeDetailService = new RemoveDetailService(database);
    private static GetAllDetailsService getAllDetailsService = new GetAllDetailsService(database);
    private static GetTotalPriceService getTotalPriceService = new GetTotalPriceService(database);
    private static UIAction addDetailUIAction = new AddDetailUIAction(addDetailService);
    private static UIAction removeDetailUIAction = new RemoveDetailUIAction(removeDetailService, getAllDetailsService);
    private static UIAction getAllDetailsUIAction = new GetAllDetailsUIAction(getAllDetailsService);
    private static UIAction getTotalPriceUIAction = new GetTotalPriceUIAction(getTotalPriceService);
    private static UIAction exitUIAction = new ExitUIAction();

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        while (true) {
            printProgramMenu();

            System.out.println("Enter menu item number to execute:");

            int userChoice = userChoiceFromMenu();

            if (userChoice < 1 || userChoice > 5) {
                System.out.println("Wrong input, try again, please use only 1 .. 5 for main menu selection.");
                continue;
            } else {
                executeSelectedMenuItem(userChoice);
            }
            System.out.println();
        }

    }

    private static void printProgramMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add detail to list");
        System.out.println("2. Delete detail from list");
        System.out.println("3. Show all detail in the list");
        System.out.println("4. See total price");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt();
//        Scanner scanner = new Scanner(System.in);
//        int userInput = scanner.nextInt();5
//
//
//        int result = 0; // looks crappy, maybe can do somehow else...
//        if (userInput) {
//            System.out.println("Wrong input, please enter only 1 .. 5 for main menu, 1 .. 7 for detail choice, 1 .. 2 for position and side menus!");
//        } else result = Integer.parseInt(userInput); // this was causing error - allow parse ONLY if it's number.
//        return result;
    }

//    private void menuSwitchLogic(int userChoice) {
//        switch (userChoice) {
//            case 1 -> {
//                APPLICATION.addDetail();
//                System.out.println("Your detail was added to list.");
//            }
//            case 2 -> {
//                System.out.println("You chose to remove details from the list. Please enter detail to remove:");
//                APPLICATION.printDetailList();
//
//                if (details.isEmpty()) {
//                    System.out.println("You don't have any detail to remove!");
//                } else {
//                    if (APPLICATION.removeDetail(userChoiceFromMenu())) {
//                        System.out.println("Your detail was removed from the list.");
//                    } else {
//                        System.out.println("Wrong input, try again, please");
//                    }
//                }
//            }
//            case 3 -> {
//                System.out.println("Detail list: ");
//                APPLICATION.printDetailList();
//                System.out.println("Detail list end.");
//            }
//            case 4 -> {
//                Detail bonnet = new DetailBuilder("Bonnet")
//                        .build();
//                Detail boot = new DetailBuilder("Boot")
//                        .build();
//                Detail frontBumper = new DetailBuilder("Bumper")
//                        .setLocation("Front")
//                        .build();
//                Detail rearBumper = new DetailBuilder("Bumper")
//                        .setLocation("Rear")
//                        .build();
//                Detail roof = new DetailBuilder("Roof")
//                        .build();
//                Detail frontLeftDoor = new DetailBuilder("Door")
//                        .setLocation("Front")
//                        .setSide("Left")
//                        .build();
//                Detail frontRightDoor = new DetailBuilder("Door")
//                        .setLocation("Front")
//                        .setSide("Right")
//                        .build();
//                Detail rearLeftDoor = new DetailBuilder("Door")
//                        .setLocation("Rear")
//                        .setSide("Left")
//                        .build();
//                Detail rearRightDoor = new DetailBuilder("Door")
//                        .setLocation("Rear")
//                        .setSide("Right")
//                        .build();
//                Detail frontLeftWing = new DetailBuilder("Wing")
//                        .setLocation("Front")
//                        .setSide("Left")
//                        .build();
//                Detail frontRightWing = new DetailBuilder("Wing")
//                        .setLocation("Front")
//                        .setSide("Right")
//                        .build();
//                Detail rearLeftWing = new DetailBuilder("Wing")
//                        .setLocation("Rear")
//                        .setSide("Left")
//                        .build();
//                Detail rearRightWing = new DetailBuilder("Wing")
//                        .setLocation("Rear")
//                        .setSide("Right")
//                        .build();
//                Detail leftWingMirror = new DetailBuilder("Wing mirror")
//                        .setSide("Left")
//                        .build();
//                Detail rightWingMirror = new DetailBuilder("Wing mirror")
//                        .setSide("Right")
//                        .build();
//
//
//                List<Detail> detailPricesList = new ArrayList<>();
//                detailPricesList.add(bonnet);
//                detailPricesList.add(boot);
//                detailPricesList.add(roof);
//                detailPricesList.add(frontBumper);
//                detailPricesList.add(rearBumper);
//                detailPricesList.add(frontLeftDoor);
//                detailPricesList.add(frontRightDoor);
//                detailPricesList.add(rearLeftDoor);
//                detailPricesList.add(rearRightDoor);
//                detailPricesList.add(frontLeftWing);
//                detailPricesList.add(frontRightWing);
//                detailPricesList.add(rearLeftWing);
//                detailPricesList.add(rearRightWing);
//                detailPricesList.add(leftWingMirror);
//                detailPricesList.add(rightWingMirror);
//
//
//                List<Detail> detailListWithPrices = new ArrayList<>();
//
//                for (Detail detail : detailPricesList) {
//                    if (details.contains(detail)) {
//                        detailListWithPrices.add(detail);
//
//                    }
//                }
//                //System.out.println(detailListWithPrices);
//
//                bonnet.setPrice(BigDecimal.valueOf(200));
//                boot.setPrice(BigDecimal.valueOf(180));
//                frontBumper.setPrice(BigDecimal.valueOf(180));
//                rearBumper.setPrice(BigDecimal.valueOf(150));
//                roof.setPrice(BigDecimal.valueOf(250));
//                frontLeftDoor.setPrice(BigDecimal.valueOf(180));
//                frontRightDoor.setPrice(BigDecimal.valueOf(180));
//                rearLeftDoor.setPrice(BigDecimal.valueOf(180));
//                rearRightDoor.setPrice(BigDecimal.valueOf(180));
//                frontLeftWing.setPrice(BigDecimal.valueOf(130));
//                frontRightWing.setPrice(BigDecimal.valueOf(130));
//                rearLeftWing.setPrice(BigDecimal.valueOf(160));
//                rearRightWing.setPrice(BigDecimal.valueOf(160));
//                leftWingMirror.setPrice(BigDecimal.valueOf(60));
//                rightWingMirror.setPrice(BigDecimal.valueOf(60));
//
//                BigDecimal totalPrice = BigDecimal.valueOf(0);
//                for (Detail detail : detailListWithPrices) {
//
//                    totalPrice = totalPrice.add(detail.getPrice());
//
//                }
//                System.out.println("Total price: " + totalPrice);
//
//
//            }
//            case 5 -> {
//                System.out.println("Good bye!");
//                System.exit(0);
//            }
//        }
//    }

//    private void addDetail() {
//        APPLICATION.details.add(DetailChoice.newDetail());
//    }

//    private void printDetailList() {
//        for (int i = 0; i < details.size(); i++) {
//            System.out.println((i + 1) + ". " + details.get(i).toString());
//        }
//    }

//    private boolean removeDetail(int removingDetailNumber) {
//        if (removingDetailNumber > 0 && removingDetailNumber <= details.size() + 1) {
//            details.remove(removingDetailNumber - 1);
//            return true;
//        }
//        return false;
//    }

    private static void executeSelectedMenuItem(int userChoice) {
        switch (userChoice) {
            case 1 -> addDetailUIAction.execute();
            case 2 -> removeDetailUIAction.execute();
            case 3 -> getAllDetailsUIAction.execute();
            case 4 -> getTotalPriceUIAction.execute();
            case 5 -> exitUIAction.execute();
        }

    }

}
