package lv.javaguru.java2.servify.detail_builder;

import lv.javaguru.java2.servify.domain.Detail;

import java.util.Scanner;

public class DetailChoice {

    public static Detail newDetail() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter detail type:");

        while (true) {
            printDetailMenu();
            int detailChoice = scanner.nextInt();

            switch (detailChoice) {
                case 1 -> {
                    System.out.println("You chose bonnet.");
                    return new DetailBuilder("Bonnet")
                            .build();
                }
                case 2 -> {
                    System.out.println("You chose boot");
                    return new DetailBuilder("Boot")
                            .build();
                }
                case 3 -> {
                    System.out.println("You chose bumper");
                    return new DetailBuilder("Bumper")
                            .setLocation(DetailLocationChoice.newDetailLocation())
                            .build();
                }
                case 4 -> {
                    System.out.println("You chose roof");
                    return new DetailBuilder("Roof")
                            .build();
                }
                case 5 -> {
                    System.out.println("You chose door");
                    return new DetailBuilder("Door")
                            .setLocation(DetailLocationChoice.newDetailLocation())
                            .setSide(DetailSideChoice.newDetailSide())
                            .build();
                }
                case 6 -> {
                    System.out.println("You chose wing");
                    return new DetailBuilder("Wing")
                            .setLocation(DetailLocationChoice.newDetailLocation())
                            .setSide(DetailSideChoice.newDetailSide())
                            .build();
                }
                case 7 -> {
                    System.out.println("You chose wing mirror");
                    return new DetailBuilder("Wing mirror")
                            .setSide(DetailSideChoice.newDetailSide())
                            .build();
                }
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }
    }

    private static void printDetailMenu() {
        System.out.println("""
                1. Bonnet
                2. Boot
                3. Bumper
                4. Roof
                5. Door\s
                6. Wing\s
                7. Wing mirror""");
    }

}
