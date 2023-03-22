package lv.javaguru.java2.servify.detail_builder;

import java.util.Scanner;

public class DetailSideChoice {



    public static String newDetailSide() {
        DetailBuilderUI print = new DetailBuilderUI();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            print.printSideMenu();

            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> {
                    return "Left";
                }
                case 2 -> {
                    return "Right";
                }
                default -> print.printChoseVariantFromList();
            }
        }
    }



}

