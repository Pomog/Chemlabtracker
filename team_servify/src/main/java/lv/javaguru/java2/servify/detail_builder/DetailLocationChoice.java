package lv.javaguru.java2.servify.detail_builder;

import java.util.Scanner;

public class DetailLocationChoice {


    public static String newDetailLocation() {
        DetailBuilderUI print = new DetailBuilderUI();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            print.printLocationMenu();

            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> {
                    return "Front";
                }
                case 2 -> {
                    return "Rear";
                }
                default -> print.printChoseVariantFromList();
            }
        }
    }



}
