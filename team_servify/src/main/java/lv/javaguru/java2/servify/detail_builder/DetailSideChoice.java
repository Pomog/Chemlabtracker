package lv.javaguru.java2.servify.detail_builder;

import java.util.Scanner;

public class DetailSideChoice {

    public static String newDetailSide() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printSideMenu();

            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> {
                    return "Left";
                }
                case 2 -> {
                    return "Right";
                }
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }
    }

    private static void printSideMenu() {
        System.out.println("""
                Enter detail side:\s
                1.Left
                2.Right""");
    }

}

