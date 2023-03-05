package DetailApp;

import java.util.Scanner;

public class DetailLocationChoise {
    Scanner scanner = new Scanner(System.in);
    String newDetailLocation;
    String detailLocationChoise(){
        System.out.println("Enter detail location: \n" +
                "1.Front\n" +
                "2.Rear");
        int locationChoise = Integer.parseInt(scanner.nextLine());
        if (locationChoise == 1) {
            newDetailLocation = "Front";
        } else if (locationChoise == 2) {
            newDetailLocation = "Rear";
        }
        return newDetailLocation;
    }
}
