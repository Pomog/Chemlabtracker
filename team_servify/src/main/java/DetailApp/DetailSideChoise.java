package DetailApp;

import java.util.Scanner;

public class DetailSideChoise {
    Scanner scanner = new Scanner(System.in);
    String newDetailSide;

    String newDetailSide() {
        while (newDetailSide == null) {
            System.out.println("""
                    Enter detail side:\s
                    1.Left
                    2.Right""");
            int userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1 -> {
                    newDetailSide = "Left";
                }
                case 2 -> {
                    newDetailSide = "Right";
                }
            }
        }
        return newDetailSide;
    }
}

