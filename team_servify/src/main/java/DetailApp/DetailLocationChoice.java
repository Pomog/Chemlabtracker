package DetailApp;

import java.util.Scanner;

class DetailLocationChoice {
    Scanner scanner = new Scanner(System.in);

    String detailLocationChoice() {

        String newDetailLocation = null;

        while (newDetailLocation == null) {
            System.out.println("""
                    Enter detail location:\s
                    1.Front
                    2.Rear""");

            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case 1 -> newDetailLocation = "Front";
                case 2 -> newDetailLocation = "Rear";
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }

        return newDetailLocation;
    }
}
