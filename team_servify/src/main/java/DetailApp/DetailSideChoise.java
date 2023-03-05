package DetailApp;

import java.util.Scanner;

public class DetailSideChoise {
    Scanner scanner = new Scanner(System.in);
    String newDetailSide;

    String newDetailSide() {
        while (newDetailSide == null) {
            System.out.println("Enter detail side: \n" +
                    "1.Left\n" +
                    "2.Right");
            int userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1: {
                    newDetailSide = "Left";
                }
                break;
                case 2: {
                    newDetailSide = "Right";
                }
                break;
            }
        }
        return newDetailSide;
    }
}

