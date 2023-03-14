package lv.javaguru.java2.servify.detail_builder;

import java.util.Scanner;

public class DetailLocationChoice {

    public static String newDetailLocation() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printLocationMenu();

            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> {
                    return "Front";
                }
                case 2 -> {
                    return "Rear";
                }
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }
    }

    private static void printLocationMenu() {
        System.out.println("""
                Enter detail location:\s
                1.Front
                2.Rear""");
    }

}
