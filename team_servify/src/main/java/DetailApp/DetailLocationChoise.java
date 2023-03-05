package DetailApp;

import java.util.Scanner;

public class DetailLocationChoise {
    Scanner scanner = new Scanner(System.in);
    String newDetailLocation;

    String detailLocationChoise() {
        while (newDetailLocation == null) {
            System.out.println("""
                    Enter detail location:\s
                    1.Front
                    2.Rear""");
            int userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1 -> {
                    newDetailLocation = "Front";
                }
                case 2 -> {
                    newDetailLocation = "Rear";
                }
            }
        }


        return newDetailLocation;
    }
}
