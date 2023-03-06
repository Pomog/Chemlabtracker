package DetailApp;

import java.util.Scanner;

class DetailSideChoice {
    Scanner scanner = new Scanner(System.in);

    String newDetailSide() {

        String newDetailSide = null;

        while (newDetailSide == null) {
            System.out.println("""
                    Enter detail side:\s
                    1.Left
                    2.Right""");

            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case 1 -> newDetailSide = "Left";
                case 2 -> newDetailSide = "Right";
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }
        return newDetailSide;
    }
}

